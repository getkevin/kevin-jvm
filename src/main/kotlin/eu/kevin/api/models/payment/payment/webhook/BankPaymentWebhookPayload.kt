package eu.kevin.api.models.payment.payment.webhook

import eu.kevin.api.models.payment.BankStatus
import eu.kevin.api.models.payment.StatusGroup
import kotlinx.serialization.Serializable

@Serializable
data class BankPaymentWebhookPayload(
    val id: String,
    val bankStatus: BankStatus,
    val statusGroup: StatusGroup,
    val type: String
)