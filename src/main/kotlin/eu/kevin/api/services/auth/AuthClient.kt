package eu.kevin.api.services.auth

import eu.kevin.api.Endpoint
import eu.kevin.api.exceptions.KevinApiErrorException
import eu.kevin.api.extensions.appendAtStartIfNotExist
import eu.kevin.api.extensions.suspendingToCompletableFuture
import eu.kevin.api.models.auth.authentication.request.StartAuthenticationRequest
import eu.kevin.api.models.auth.authentication.request.StartAuthenticationRequestBody
import eu.kevin.api.models.auth.authentication.response.StartAuthenticationResponse
import eu.kevin.api.models.auth.token.request.ReceiveTokenRequest
import eu.kevin.api.models.auth.token.request.RefreshTokenRequest
import eu.kevin.api.models.auth.token.response.ReceiveTokenResponse
import eu.kevin.api.models.auth.tokenContent.ReceiveTokenContentRequest
import eu.kevin.api.models.auth.tokenContent.ReceiveTokenContentResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.util.concurrent.CompletableFuture

/**
 * Implements API Methods of the [Authentication service](https://api-reference.kevin.eu/public/platform/v0.3#tag/Authentication-Service)
 */
class AuthClient internal constructor(
    private val httpClient: HttpClient
) {
    /**
     * API Method: [Start authentication](https://api-reference.kevin.eu/public/platform/v0.3#tag/Authentication-Service/operation/startAuth)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun startAuthentication(request: StartAuthenticationRequest): StartAuthenticationResponse =
        httpClient.post {
            url(path = Endpoint.Paths.Auth.startAuthentication())
            setBody(
                StartAuthenticationRequestBody(
                    email = request.email,
                    cardMethod = request.cardMethod
                )
            )
            request.run {
                parameter("bankId", bankId)
                parameter("redirectPreferred", redirectPreferred)
                scopes?.forEach { parameter("scopes", it) }
                parameter("lang", request.lang)

                headers {
                    append("Request-Id", requestId)
                    append("Redirect-URL", redirectUrl)
                    webhookUrl?.let { append("Webhook-URL", it) }
                }
            }
        }.body()

    /**
     * Equivalent of suspending `startAuthentication(request: StartAuthenticationRequest)` for Java interoperability
     */
    @JvmName("startAuthentication")
    fun startAuthenticationAsFuture(request: StartAuthenticationRequest): CompletableFuture<StartAuthenticationResponse> {
        return suspendingToCompletableFuture { startAuthentication(request) }
    }

    /**
     * API Method: [Receive token](https://api-reference.kevin.eu/public/platform/v0.3#tag/Authentication-Service/operation/receiveToken), with `grantType` set to `authorizationCode`
     */
    @Throws(KevinApiErrorException::class)
    suspend fun receiveToken(request: ReceiveTokenRequest): ReceiveTokenResponse =
        httpClient.post {
            url(path = Endpoint.Paths.Auth.receiveToken())
            setBody(request)
        }.body()

    /**
     * Equivalent of suspending `receiveToken(request: ReceiveTokenRequest)` for Java interoperability
     */
    @JvmName("receiveToken")
    fun receiveTokenAsFuture(request: ReceiveTokenRequest): CompletableFuture<ReceiveTokenResponse> {
        return suspendingToCompletableFuture { receiveToken(request) }
    }

    /**
     * API Method: [Receive token](https://api-reference.kevin.eu/public/platform/v0.3#tag/Authentication-Service/operation/receiveToken), with `grantType` set to `refreshToken`
     */
    @Throws(KevinApiErrorException::class)
    suspend fun refreshToken(request: RefreshTokenRequest): ReceiveTokenResponse =
        httpClient.post {
            url(path = Endpoint.Paths.Auth.receiveToken())
            setBody(request)
        }.body()

    /**
     * Equivalent of suspending `refreshToken(request: RefreshTokenRequest)` for Java interoperability
     */
    @JvmName("refreshToken")
    fun refreshTokenAsFuture(request: RefreshTokenRequest): CompletableFuture<ReceiveTokenResponse> {
        return suspendingToCompletableFuture { refreshToken(request) }
    }

    /**
     * API Method: [Receive token content](https://api-reference.kevin.eu/public/platform/v0.3#tag/Authentication-Service/operation/receiveTokenContent)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun receiveTokenContent(request: ReceiveTokenContentRequest): ReceiveTokenContentResponse =
        httpClient.get {
            url(path = Endpoint.Paths.Auth.receiveTokenContent())
            headers {
                append(HttpHeaders.Authorization, request.accessToken.appendAtStartIfNotExist("Bearer "))
            }
        }.body()

    /**
     * Equivalent of suspending `receiveTokenContent(request: ReceiveTokenContentRequest)` for Java interoperability
     */
    @JvmName("receiveTokenContent")
    fun receiveTokenContentAsFuture(request: ReceiveTokenContentRequest): CompletableFuture<ReceiveTokenContentResponse> {
        return suspendingToCompletableFuture { receiveTokenContent(request) }
    }
}
