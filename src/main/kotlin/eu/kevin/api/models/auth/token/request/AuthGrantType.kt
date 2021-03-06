package eu.kevin.api.models.auth.token.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AuthGrantType {
    @SerialName("authorizationCode") AUTHORIZATION_CODE,
    @SerialName("refreshToken") REFRESH_TOKEN
}
