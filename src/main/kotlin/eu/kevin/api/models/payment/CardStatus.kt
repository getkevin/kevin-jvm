package eu.kevin.api.models.payment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class CardStatus {
    @SerialName("started") STARTED,
    @SerialName("issued") ISSUED,
    @SerialName("paid") PAID,
    @SerialName("payment_success") PAYMENT_SUCCESS,
    @SerialName("payment_failure") PAYMENT_FAILURE,
    @SerialName("hold") HOLD,
    @SerialName("canceled") CANCELED,
    @SerialName("in_progress") IN_PROGRESS,
    @SerialName("invoice_viewed") INVOICE_VIEWED,
    @SerialName("invoice_refunded") INVOICE_REFUNDED,
    @SerialName("invoice_reversal") INVOICE_REVERSAL,
    @SerialName("refund_failure") REFUND_FAILURE,
    @SerialName("invoice_refund_reversed") INVOICE_REFUND_REVERSED,
    @SerialName("refund_init_failure") REFUND_INIT_FAILURE,
    @SerialName("reversal_init_failure") REVERSAL_INIT_FAILURE,
    @SerialName("reversal_failure") REVERSAL_FAILURE,
    @SerialName("refund_in_progress") REFUND_IN_PROGRESS,
    @SerialName("reversal_in_progress") REVERSAL_IN_PROGRESS,
    @SerialName("received") RECEIVED,
    @SerialName("rejected") REJECTED,
    @SerialName("expired") EXPIRED,
    @SerialName("chargeback") CHARGEBACK,
    @SerialName("representation") REPRESENTATION,
    @SerialName("retrieval") RETRIEVAL,
    @SerialName("prearbitrationgood_faith") PREARBITRATIONGOOD_FAITH,
    @SerialName("good_faith") GOOD_FAITH,
    @SerialName("fraud_advice") FRAUD_ADVICE,
    @SerialName("failed") FAILED,
    @SerialName("refund_forbidden") REFUND_FORBIDDEN
}