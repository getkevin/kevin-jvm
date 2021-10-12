package eu.kevin.api.models.auth.startAuthentication.request

import kotlinx.serialization.Serializable

@Serializable
data class StartAuthenticationRequestBody(
    val email: String? = null,
    val cardMethod: CardMethod? = null
)
