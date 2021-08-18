package eu.kevin.api

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class Client internal constructor(
    private val authorization: Authorization,
    private val version: EndpointVersion,
    private val httpClient: HttpClient
) {
    constructor(
        authorization: Authorization,
        version: EndpointVersion
    ) : this(
        authorization = authorization,
        version = version,
        httpClient = Dependencies.httpClient
    )

    constructor(
        authorization: Authorization
    ) : this(
        authorization = authorization,
        version = EndpointVersion.V03,
    )

    internal val client = httpClient.config {
        defaultRequest {
            url.takeFrom(URLBuilder().takeFrom("${Endpoint.BASE}${version.path}").apply {
                encodedPath += url.encodedPath
            })
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