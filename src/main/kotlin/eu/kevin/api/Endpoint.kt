package eu.kevin.api

internal object Endpoint {
    const val BASE = "https://api.kevin.eu/platform"
    const val VERSION = "/v0.3"

    object Path {
        fun initiatePayment() = "/pis/payment"
        fun getPaymentStatus(paymentId: String) = "/pis/payment/$paymentId/status"
        fun initiatePaymentRefund(paymentId: String) = "/pis/payment/$paymentId/refunds"
        fun startAuthentication() = "/auth"
        fun receiveToken() = "/auth/token"
        fun receiveTokenContent() = "/auth/token/content"
    }
}
