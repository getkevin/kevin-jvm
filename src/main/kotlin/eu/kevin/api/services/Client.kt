package eu.kevin.api.services

import eu.kevin.api.Dependencies
import eu.kevin.api.Endpoint
import eu.kevin.api.models.Authorization
import eu.kevin.api.services.account.AccountClient
import eu.kevin.api.services.auth.AuthClient
import eu.kevin.api.services.general.GeneralClient
import eu.kevin.api.services.payment.PaymentClient
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import java.net.URI

class Client internal constructor(
    private val authorization: Authorization,
    private val apiUrl: String,
    private val httpClient: HttpClient,
    private val serializer: Json
) {
    val paymentClient by lazy { PaymentClient(httpClient = httpClient.withAuthorization()) }
    val authClient by lazy { AuthClient(httpClient = httpClient.withAuthorization()) }
    val generalClient by lazy { GeneralClient(httpClient = httpClient.withAuthorization()) }
    val accountClient by lazy { AccountClient(httpClient = httpClient.withAuthorization(), serializer = serializer) }

    constructor(
        authorization: Authorization,
        apiUrl: String = Endpoint.BASE
    ) : this(
        authorization = authorization,
        apiUrl = apiUrl,
        httpClient = Dependencies.httpClient,
        serializer = Dependencies.serializer
    )

    private fun HttpClient.withAuthorization() = this.config {
        defaultRequest {
            url.takeFrom(
                URLBuilder().takeFrom(
                    URI.create("${apiUrl}/").resolve(".${Endpoint.VERSION}")
                ).apply {
                    encodedPath += url.encodedPath
                }
            )
            header("Client-Id", authorization.clientId)
            header("Client-Secret", authorization.clientSecret)
        }
    }
}
