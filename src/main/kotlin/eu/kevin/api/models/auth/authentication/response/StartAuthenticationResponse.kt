package eu.kevin.api.models.auth.authentication.response

import kotlinx.serialization.Serializable

@Serializable
data class StartAuthenticationResponse(
    var authorizationLink: String,
    val state: String
)
