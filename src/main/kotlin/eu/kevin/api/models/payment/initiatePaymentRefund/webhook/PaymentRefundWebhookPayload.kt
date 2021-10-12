package eu.kevin.api.models.payment.initiatePaymentRefund.webhook

import eu.kevin.api.models.payment.StatusGroup
import kotlinx.serialization.Serializable

@Serializable
data class PaymentRefundWebhookPayload(
    val id: String,
    val paymentId: String,
    val statusGroup: StatusGroup,
    val type: String
)