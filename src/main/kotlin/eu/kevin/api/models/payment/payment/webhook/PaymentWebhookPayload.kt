package eu.kevin.api.models.payment.payment.webhook

import eu.kevin.api.models.payment.BankStatus
import eu.kevin.api.models.payment.CardStatus
import eu.kevin.api.models.payment.HybridStatus
import eu.kevin.api.models.payment.StatusGroup
import kotlinx.serialization.Serializable

@Serializable
data class PaymentWebhookPayload(
    val id: String,
    val bankStatus: BankStatus? = null,
    val cardStatus: CardStatus? = null,
    val hybridStatus: HybridStatus? = null,
    val statusGroup: StatusGroup,
    val type: String,
)
