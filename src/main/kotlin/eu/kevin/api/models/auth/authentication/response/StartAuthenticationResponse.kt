package eu.kevin.api.models.auth.authentication.response

import kotlinx.serialization.Serializable

@Serializable
data class StartAuthenticationResponse(
    val authorizationLink: String,
    val state: String
)
