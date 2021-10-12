package eu.kevin.api.services.auth

import eu.kevin.api.Endpoint
import eu.kevin.api.models.auth.receiveToken.request.ReceiveTokenRequest
import eu.kevin.api.models.auth.receiveToken.request.RefreshTokenRequest
import eu.kevin.api.models.auth.receiveToken.response.ReceiveTokenResponse
import eu.kevin.api.models.auth.receiveTokenContent.ReceiveTokenContentRequest
import eu.kevin.api.models.auth.receiveTokenContent.ReceiveTokenContentResponse
import eu.kevin.api.models.auth.startAuthentication.request.StartAuthenticationRequest
import eu.kevin.api.models.auth.startAuthentication.request.StartAuthenticationRequestBody
import eu.kevin.api.models.auth.startAuthentication.response.StartAuthenticationResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthClient internal constructor(
    private val httpClient: HttpClient
) {
    suspend fun startAuthentication(request: StartAuthenticationRequest): StartAuthenticationResponse =
        httpClient.post(
            path = Endpoint.Path.startAuthentication(),
            body = StartAuthenticationRequestBody(
                email = request.email,
                cardMethod = request.cardMethod
            )
        ) {
            request.run {
                parameter("bankId", bankId)
                parameter("redirectPreferred", redirectPreferred)
                scopes?.forEach {
                    parameter("scopes", it)
                }

                headers {
                    append("Request-Id", requestId)
                    append("Redirect-URL", redirectUrl)
                }
            }
        }

    suspend fun receiveToken(request: ReceiveTokenRequest): ReceiveTokenResponse =
        httpClient.post(
            path = Endpoint.Path.receiveToken(),
            body = request
        )

    suspend fun refreshToken(request: RefreshTokenRequest): ReceiveTokenResponse =
        httpClient.post(
            path = Endpoint.Path.receiveToken(),
            body = request
        )

    suspend fun receiveTokenContent(request: ReceiveTokenContentRequest): ReceiveTokenContentResponse =
        httpClient.get(
            path = Endpoint.Path.receiveTokenContent()
        ) {
            headers {
                append(HttpHeaders.Authorization, "Bearer ${request.accessToken}")
            }
        }
}