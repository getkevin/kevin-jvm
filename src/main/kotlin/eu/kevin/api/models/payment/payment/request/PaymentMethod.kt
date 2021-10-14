package eu.kevin.api.models.payment.payment.request

import kotlinx.serialization.Serializable

@Serializable
enum class PaymentMethod(val title: String) {
    BANK("bank"),
    CARD("card")
}
