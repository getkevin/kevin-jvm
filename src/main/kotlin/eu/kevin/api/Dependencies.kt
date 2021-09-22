package eu.kevin.api

import eu.kevin.api.exceptions.KevinApiErrorException
import eu.kevin.api.serializers.BigDecimalSerializer
import eu.kevin.api.serializers.LocalDateSerializer
import eu.kevin.api.serializers.LocalDateTimeSerializer
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

internal object Dependencies {
    val httpClient by lazy {
        HttpClient(CIO) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Dependencies.serializer)
            }
            defaultRequest {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            HttpResponseValidator {
                handleResponseException { exception ->
                    when (exception) {
                        is ResponseException -> {
                            val status = exception.response.status
                            throw KevinApiErrorException(
                                responseStatusCode = status.value,
                                responseBody = if (status == HttpStatusCode.BadRequest)
                                    serializer.decodeFromString(exception.response.readText())
                                else null
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
        }
    }
}
