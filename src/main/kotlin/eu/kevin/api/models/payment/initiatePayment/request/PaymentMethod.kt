package eu.kevin.api.models.payment.initiatePayment.request

import kotlinx.serialization.Serializable

@Serializable
enum class PaymentMethod(val title: String) {
    BANK("bank"),
    CARD("card")
}
