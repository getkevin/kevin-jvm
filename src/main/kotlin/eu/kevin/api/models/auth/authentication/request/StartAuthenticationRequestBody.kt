package eu.kevin.api.models.auth.authentication.request

import kotlinx.serialization.Serializable

@Serializable
data class StartAuthenticationRequestBody(
    val email: String? = null,
    val cardMethod: CardMethod? = null
)
