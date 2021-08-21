package eu.kevin.api.payments.initiatePayment.request

import kotlinx.serialization.Serializable

@Serializable
data class UserIdentifier(
    val email: String
)