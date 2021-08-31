package eu.kevin.api

import eu.kevin.api.models.exception.KevinApiClientErrorException
import eu.kevin.api.serialization.BigDecimalSerializer
import eu.kevin.api.serialization.LocalDateSerializer
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
                        is ClientRequestException -> {
                            when (exception.response.status) {
                                HttpStatusCode.BadRequest -> {
                                    throw KevinApiClientErrorException(
                                        response = serializer.decodeFromString(exception.response.readText())
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private val serializer: Json by lazy {
        Json {
            serializersModule = SerializersModule {
                contextual(LocalDate::class, LocalDateSerializer)
                contextual(BigDecimal::class, BigDecimalSerializer)
            }
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
}
