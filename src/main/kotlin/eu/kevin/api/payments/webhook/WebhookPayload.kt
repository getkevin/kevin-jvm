package eu.kevin.api.payments.webhook

import eu.kevin.api.payments.initiatePayment.response.BankStatus
import eu.kevin.api.payments.initiatePayment.response.StatusGroup
import kotlinx.serialization.Serializable

@Serializable
data class WebhookPayload(
    val id: String,
    val bankStatus: BankStatus,
    val statusGroup: StatusGroup,
    val type: String
)