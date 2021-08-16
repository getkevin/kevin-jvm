package eu.kevin.api

import io.ktor.client.*

sealed class Authorization {
    data class Default(val clientId: String, val clientSecret: String): Authorization()
    data class PointOfSale(val linkId: String): Authorization()
}

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
}