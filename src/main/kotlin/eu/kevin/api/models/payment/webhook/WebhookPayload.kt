package eu.kevin.api.models.payment.webhook

import eu.kevin.api.models.payment.initiatePayment.response.BankStatus
import eu.kevin.api.models.payment.initiatePayment.response.StatusGroup
import kotlinx.serialization.Serializable

@Serializable
data class WebhookPayload(
    val id: String,
    val bankStatus: BankStatus,
    val statusGroup: StatusGroup,
    val type: String
)