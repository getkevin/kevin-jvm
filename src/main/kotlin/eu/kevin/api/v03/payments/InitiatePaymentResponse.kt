package eu.kevin.api.v03.payments

import com.squareup.moshi.Json

data class InitiatePaymentResponse(
    val id: String,
    val cardStatus: CardStatus?,
    val bankStatus: BankStatus?,
    val hybridStatus: HybridStatus?,
    val statusGroup: StatusGroup,
    val confirmLink: String
)

enum class CardStatus {
    @Json(name = "started") Started,
    @Json(name = "issued") Issued,
    @Json(name = "paid") Paid,
    @Json(name = "payment_success") PaymentSuccess,
    @Json(name = "payment_failure") PaymentFailure,
    @Json(name = "hold") Hold,
    @Json(name = "canceled") Canceled,
    @Json(name = "in_progress") InProgress,
    @Json(name = "invoice_viewed") InvoiceViewed,
    @Json(name = "invoice_refunded") InvoiceRefunded,
    @Json(name = "invoice_reversal") InvoiceReversal,
    @Json(name = "refund_failure") RefundFailure,
    @Json(name = "invoice_refund_reversed") InvoiceRefundReversed,
    @Json(name = "refund_init_failure") RefundInitFailure,
    @Json(name = "reversal_init_failure") ReversalInitFailure,
    @Json(name = "reversal_failure") ReversalFailure,
    @Json(name = "refund_in_progress") RefundInProgress,
    @Json(name = "reversal_in_progress") ReversalInProgress,
    @Json(name = "received") Received,
    @Json(name = "rejected") Rejected,
    @Json(name = "expired") Expired,
    @Json(name = "chargeback") Chargeback,
    @Json(name = "representation") Representation,
    @Json(name = "retrieval") Retrieval,
    @Json(name = "prearbitrationgood_faith") PrearbitrationgoodFaith,
    @Json(name = "good_faith") GoodFaith,
    @Json(name = "fraud_advice") FraudAdvice,
    @Json(name = "failed") Failed,
    @Json(name = "refund_forbidden") RefundForbidden
}

enum class BankStatus {
    STRD,
    ACCC,
    ACCP,
    ACSC,
    ACSP,
    ACTC,
    ACWC,
    ACWP,
    RCVD,
    PDNG,
    RJCT,
    CANC,
    ACFC,
    PATC,
}

enum class HybridStatus {
    @Json(name = "created") Created,
    @Json(name = "canceled") Canceled,
    @Json(name = "expired") Expired,
}

enum class StatusGroup {
    @Json(name = "started") Started,
    @Json(name = "pending") Pending,
    @Json(name = "completed") Completed,
    @Json(name = "failed") Failed,
}
