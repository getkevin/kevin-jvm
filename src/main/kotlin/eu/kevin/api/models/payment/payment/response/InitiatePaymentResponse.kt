package eu.kevin.api.models.payment.payment.response

import eu.kevin.api.models.payment.BankStatus
import eu.kevin.api.models.payment.CardStatus
import eu.kevin.api.models.payment.HybridStatus
import eu.kevin.api.models.payment.StatusGroup
import kotlinx.serialization.Serializable

@Serializable
data class InitiatePaymentResponse @JvmOverloads constructor(
    val id: String,
    val statusGroup: StatusGroup,
    val confirmLink: String,
    var cardStatus: CardStatus? = null,
    var bankStatus: BankStatus? = null,
    var hybridStatus: HybridStatus? = null,
)
