package eu.kevin.api.models.payment.getPaymentStatus

import eu.kevin.api.models.payment.BankStatus
import eu.kevin.api.models.payment.CardStatus
import eu.kevin.api.models.payment.HybridStatus
import eu.kevin.api.models.payment.StatusGroup
import kotlinx.serialization.Serializable

@Serializable
data class GetPaymentStatusResponse(
    val cardStatus: CardStatus? = null,
    val bankStatus: BankStatus? = null,
    val hybridStatus: HybridStatus? = null,
    val group: StatusGroup,
    val error: GetPaymentStatusResponseError? = null
)
