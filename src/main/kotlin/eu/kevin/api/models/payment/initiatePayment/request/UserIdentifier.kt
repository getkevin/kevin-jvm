package eu.kevin.api.models.payment.initiatePayment.request

import kotlinx.serialization.Serializable

@Serializable
data class UserIdentifier(
    val email: String
)