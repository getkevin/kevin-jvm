package eu.kevin.api.services

import eu.kevin.api.Dependencies
import eu.kevin.api.Endpoint
import eu.kevin.api.models.Authorization
import eu.kevin.api.services.account.AccountClient
import eu.kevin.api.services.auth.AuthClient
import eu.kevin.api.services.general.GeneralClient
import eu.kevin.api.services.payment.PaymentClient
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.net.URI

class Client internal constructor(
    private val authorization: Authorization,
    private val apiUrl: String,
    private val httpClient: HttpClient,
    private val customHeaders: Map<String, String>
) {
    val paymentClient by lazy { PaymentClient(httpClient = httpClient.withAuthorization()) }
    val authClient by lazy { AuthClient(httpClient = httpClient.withAuthorization()) }
    val generalClient by lazy { GeneralClient(httpClient = httpClient.withAuthorization()) }
    val accountClient by lazy { AccountClient(httpClient = httpClient.withAuthorization()) }

    constructor(
        authorization: Authorization,
        apiUrl: String = Endpoint.BASE,
        customHeaders: Map<String, String> = mapOf()
    ) : this(
        authorization = authorization,
        apiUrl = apiUrl,
        httpClient = Dependencies.httpClient,
        customHeaders = customHeaders
    )

    private fun HttpClient.withAuthorization() = this.config {
        defaultRequest {
            url.takeFrom(URI.create("$apiUrl/").resolve(".${Endpoint.VERSION}/"))
            header("Client-Id", authorization.clientId)
            header("Client-Secret", authorization.clientSecret)
            customHeaders.forEach { header(it.key, it.value) }
        }
    }
}
