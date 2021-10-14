package eu.kevin.api.models.payment.paymentStatus

import eu.kevin.api.models.ErrorCode
import kotlinx.serialization.Serializable

@Serializable
data class GetPaymentStatusResponseError(
    val code: ErrorCode
)
