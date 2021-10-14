package eu.kevin.api.models.auth.token.request

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequest(
    val refreshToken: String
) {
    @Required
    private val grantType = AuthGrantType.REFRESH_TOKEN
}
