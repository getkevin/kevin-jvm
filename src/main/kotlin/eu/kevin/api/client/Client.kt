package eu.kevin.api.client

import eu.kevin.api.Dependencies
import eu.kevin.api.Endpoint
import eu.kevin.api.models.Authorization
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class Client internal constructor(
    private val authorization: Authorization,
    private val apiUrl: String,
    private val httpClient: HttpClient,
) {
    val paymentsClient by lazy {
        PaymentsClient(
            httpClient = httpClient.withAuthorization()
        )
    }

    constructor(authorization: Authorization, apiUrl: String) : this(
        authorization = authorization,
        apiUrl = apiUrl,
        httpClient = Dependencies.httpClient,
    )

    constructor(authorization: Authorization) : this(
        authorization = authorization,
        apiUrl = Endpoint.BASE
    )

    private fun HttpClient.withAuthorization() = this.config {
        defaultRequest {
            url.takeFrom(
                URLBuilder().takeFrom("${apiUrl}${Endpoint.VERSION}").apply {
                    encodedPath += url.encodedPath
                }
            )
            when (authorization) {
                is Authorization.Default -> {
                    header("Client-Id", authorization.clientId)
                    header("Client-Secret", authorization.clientSecret)
                }
                is Authorization.PointOfSale -> {
                    header("Link-Id", authorization.linkId)
                }
            }
        }
    }
}
