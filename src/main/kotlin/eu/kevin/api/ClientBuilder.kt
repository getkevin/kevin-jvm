package eu.kevin.api

import eu.kevin.api.models.Authorization
import eu.kevin.api.models.EndpointVersion
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import eu.kevin.api.v03.Client as ClientV03

object ClientBuilder {
    internal fun buildV03(
        authorization: Authorization,
        httpClient: HttpClient,
        serializer: Serializer
    ) = ClientV03(
        httpClient = httpClient.withAuthorizationAndVersion(
            authorization = authorization,
            version = EndpointVersion.V03
        ),
        serializer = serializer
    )

    fun buildV03(authorization: Authorization) = buildV03(
        authorization = authorization,
        httpClient = Dependencies.httpClient,
        serializer = Dependencies.serializer
    )

    private fun HttpClient.withAuthorizationAndVersion(
        authorization: Authorization,
        version: EndpointVersion
    ) = this.config {
        defaultRequest {
            url.takeFrom(
                URLBuilder().takeFrom("${Endpoint.BASE}${version.path}").apply {
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
