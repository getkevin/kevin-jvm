package eu.kevin.api.services.auth

import eu.kevin.api.Endpoint
import eu.kevin.api.models.auth.authentication.request.StartAuthenticationRequest
import eu.kevin.api.models.auth.authentication.request.StartAuthenticationRequestBody
import eu.kevin.api.models.auth.authentication.response.StartAuthenticationResponse
import eu.kevin.api.models.auth.token.request.ReceiveTokenRequest
import eu.kevin.api.models.auth.token.request.RefreshTokenRequest
import eu.kevin.api.models.auth.token.response.ReceiveTokenResponse
import eu.kevin.api.models.auth.tokenContent.ReceiveTokenContentRequest
import eu.kevin.api.models.auth.tokenContent.ReceiveTokenContentResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthClient internal constructor(
    private val httpClient: HttpClient
) {
    suspend fun startAuthentication(request: StartAuthenticationRequest): StartAuthenticationResponse =
        httpClient.post(
            path = Endpoint.Paths.Auth.startAuthentication(),
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
            path = Endpoint.Paths.Auth.receiveToken(),
            body = request
        )

    suspend fun refreshToken(request: RefreshTokenRequest): ReceiveTokenResponse =
        httpClient.post(
            path = Endpoint.Paths.Auth.receiveToken(),
            body = request
        )

    suspend fun receiveTokenContent(request: ReceiveTokenContentRequest): ReceiveTokenContentResponse =
        httpClient.get(
            path = Endpoint.Paths.Auth.receiveTokenContent()
        ) {
            headers {
                append(HttpHeaders.Authorization, "Bearer ${request.accessToken}")
            }
        }
}