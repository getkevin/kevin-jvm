package eu.kevin.api.payments.initiatePayment.response

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
