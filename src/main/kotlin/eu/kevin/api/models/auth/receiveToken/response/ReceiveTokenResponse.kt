package eu.kevin.api.models.auth.receiveToken.response

import kotlinx.serialization.Serializable

@Serializable
data class ReceiveTokenResponse(
    val tokenType: String,
    val accessToken: String,
    val accessTokenExpiresIn: Long,
    val refreshToken: String,
    val refreshTokenExpiresIn: Long
)
