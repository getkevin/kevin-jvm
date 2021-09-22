package eu.kevin.api.models.payment.initiatePayment.request

import kotlinx.serialization.Serializable

@Serializable
data class CardPaymentMethod @JvmOverloads constructor(
    var cvc: String? = null,
    var expMonth: Int? = null,
    var expYear: Int? = null,
    var number: String? = null,
    var holderName: String? = null
)
