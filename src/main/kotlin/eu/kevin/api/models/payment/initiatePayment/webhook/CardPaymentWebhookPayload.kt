package eu.kevin.api.models.payment.initiatePayment.webhook

import eu.kevin.api.models.payment.CardStatus
import eu.kevin.api.models.payment.StatusGroup
import kotlinx.serialization.Serializable

@Serializable
data class CardPaymentWebhookPayload(
    val id: String,
    val cardStatus: CardStatus,
    val statusGroup: StatusGroup,
    val type: String
)