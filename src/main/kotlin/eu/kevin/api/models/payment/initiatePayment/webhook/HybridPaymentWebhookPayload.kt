package eu.kevin.api.models.payment.initiatePayment.webhook

import eu.kevin.api.models.payment.HybridStatus
import eu.kevin.api.models.payment.StatusGroup
import kotlinx.serialization.Serializable

@Serializable
data class HybridPaymentWebhookPayload(
    val id: String,
    val hybridStatus: HybridStatus,
    val statusGroup: StatusGroup,
    val type: String
)