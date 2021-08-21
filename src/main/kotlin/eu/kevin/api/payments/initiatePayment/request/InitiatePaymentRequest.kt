package eu.kevin.api.payments.initiatePayment.request

import kotlinx.serialization.Serializable

@Serializable
data class InitiatePaymentRequest @JvmOverloads constructor(
    val redirectUrl: String,
    val paymentData: InitiatePaymentRequestBody,
    var accessToken: String? = null,
    var bankId: String? = null,
    var redirectPreferred: Boolean? = null,
    var paymentMethodPreferred: PaymentMethod? = null,
    var webhookUrl: String? = null,
)
