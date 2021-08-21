package eu.kevin.api.payments.initiatePayment.request

import kotlinx.serialization.Serializable

@Serializable
enum class PaymentMethod(val title: String) {
    BANK("bank"),
    CARD("card")
}
