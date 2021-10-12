package eu.kevin.api.models.payment.getPaymentStatus

data class GetPaymentStatusRequest @JvmOverloads constructor(
    val paymentId: String,
    var accessToken: String? = null
)
