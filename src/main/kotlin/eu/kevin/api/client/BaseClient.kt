package eu.kevin.api.client

import eu.kevin.api.Dependencies
import eu.kevin.api.models.Authorization
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

abstract class BaseClient internal constructor(
    private val authorization: Authorization,
    private val httpClient: HttpClient
) {
    protected val client = httpClient.withAuthorization(authorization)

    constructor(authorization: Authorization) : this(
        authorization = authorization,
        httpClient = Dependencies.httpClient,
    )

    private fun HttpClient.withAuthorization(
        authorization: Authorization,
    ) = this.config {
        defaultRequest {
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
