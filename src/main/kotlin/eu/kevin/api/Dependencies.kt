package eu.kevin.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import eu.kevin.api.models.exception.KevinApiClientErrorException
import eu.kevin.api.serialization.BigDecimalAdapter
import eu.kevin.api.serialization.LocalDateAdapter
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

internal object Dependencies {
    val httpClient by lazy {
        HttpClient(CIO) {
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
                                        response = serializer.deserialize(exception.response.readText())
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    val serializer by lazy {
        Serializer(moshi = moshi)
    }
    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(LocalDateAdapter)
            .add(BigDecimalAdapter)
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }
}
