package eu.kevin.api.models.auth.token.response

import kotlinx.serialization.Serializable

@Serializable
data class ReceiveTokenResponse(
    val authId: String? = null,
    val tokenType: String,
    val accessToken: String,
    val accessTokenExpiresIn: Long,
    val refreshToken: String,
    val refreshTokenExpiresIn: Long
)
