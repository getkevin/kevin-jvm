package eu.kevin.api

import eu.kevin.api.exceptions.KevinApiErrorException
import eu.kevin.api.serializers.BigDecimalSerializer
import eu.kevin.api.serializers.LocalDateSerializer
import eu.kevin.api.serializers.LocalDateTimeSerializer
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

object Dependencies {
    val httpClient by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(serializer)
                expectSuccess = true
            }
            defaultRequest {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    when (exception) {
                        is ResponseException -> {
                            val status = exception.response.status
                            throw KevinApiErrorException(
                                responseStatusCode = status.value,
                                responseBody = if (status == HttpStatusCode.BadRequest)
                                    serializer.decodeFromString(exception.response.bodyAsText())
                                else null,
                                externalMessage = exception.message
                            )
                        }
                    }
                }
            }
        }
    }
    val serializer: Json by lazy {
        Json {
            serializersModule = SerializersModule {
                contextual(LocalDate::class, LocalDateSerializer)
                contextual(BigDecimal::class, BigDecimalSerializer)
                contextual(LocalDateTime::class, LocalDateTimeSerializer)
            }
            ignoreUnknownKeys = true
            isLenient = true

            // use explicitNulls=false flag instead when kotlinx.serialization 1.3 becomes stable
            // to skip serialization of properties with null values
            // https://github.com/Kotlin/kotlinx.serialization/commit/2048fac0cae7f798abc1f2a1cf22a877e9f66430
            encodeDefaults = false
        }
    }
}
