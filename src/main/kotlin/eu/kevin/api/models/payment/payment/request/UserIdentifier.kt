package eu.kevin.api.models.payment.payment.request

import kotlinx.serialization.Serializable

@Serializable
data class UserIdentifier(
    val email: String
)