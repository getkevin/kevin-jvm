package eu.kevin.api

internal object Endpoint {
    const val BASE = "https://api.kevin.eu/platform"
    const val VERSION = "/v0.3"

    object Path {
        fun initiatePayment() = "/pis/payment"
        fun initiatePaymentRefund(paymentId: String) = "/pis/payment/$paymentId/refunds"
    }
}
