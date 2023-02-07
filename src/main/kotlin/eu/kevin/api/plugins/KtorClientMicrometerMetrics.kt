package eu.kevin.api.plugins

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.utils.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags
import io.micrometer.core.instrument.Timer
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

/**
 * Feature to get statistics from KTor client. Partially lifted off https://youtrack.jetbrains.com/issue/KTOR-1004
 */
class KtorClientMicrometerMetrics internal constructor(val meterRegistry: MeterRegistry) {
    private fun stopTimer(sample: Timer.Sample, url: Url, path: String, method: String, status: Int, throwable: Throwable?) {
        val parameters = mutableMapOf(
            "host" to url.host,
            "path" to path,
            "method" to method,
            "status" to status.toString()
        )

        throwable?.let {
            parameters["throwable"] = it.javaClass.simpleName
        }

        sample.stop(
            meterRegistry.timer(
                "http.client.requests",
                Tags.of((parameters).map { Tag.of(it.key, it.value) })
            )
        )
    }

    class Config {
        lateinit var meterRegistry: MeterRegistry
    }

    companion object Feature : HttpClientFeature<Config, KtorClientMicrometerMetrics> {
        override val key: AttributeKey<KtorClientMicrometerMetrics> = AttributeKey("ClientMetrics")

        val pathAttributeKey: AttributeKey<String> = AttributeKey("Path")
        private val sampleAttributeKey: AttributeKey<Timer.Sample> = AttributeKey("TimerSample")
        private val monitoringPhase = PipelinePhase("Monitoring")

        override fun prepare(block: Config.() -> Unit): KtorClientMicrometerMetrics {
            val config = Config().apply(block)
            return KtorClientMicrometerMetrics(config.meterRegistry)
        }

        @OptIn(InternalAPI::class)
        override fun install(plugin: KtorClientMicrometerMetrics, scope: HttpClient) {
            scope.receivePipeline.insertPhaseAfter(HttpReceivePipeline.Before, monitoringPhase)

            scope.sendPipeline.intercept(HttpSendPipeline.Monitoring) {
                val sample = Timer.start(plugin.meterRegistry)
                try {
                    context.attributes.put(sampleAttributeKey, sample)
                    proceed()
                } catch (cause: Throwable) {
                    val url = context.url.build()

                    plugin.stopTimer(
                        sample = sample,
                        url = url,
                        path = context.attributes.getOrNull(pathAttributeKey)
                            ?: URLDecoder.decode(url.encodedPath, StandardCharsets.UTF_8),
                        method = context.method.value,
                        status = 500,
                        throwable = cause
                    )
                    throw cause.unwrapCancellationException()
                }
            }

            scope.receivePipeline.intercept(monitoringPhase) {
                val url = it.call.request.url

                plugin.stopTimer(
                    sample = it.request.attributes[sampleAttributeKey],
                    url = url,
                    path = it.request.attributes.getOrNull(pathAttributeKey)
                        ?: URLDecoder.decode(url.encodedPath, StandardCharsets.UTF_8),
                    method = it.request.method.value,
                    status = it.call.response.status.value,
                    throwable = null
                )
                proceed()
            }
        }
    }
}
