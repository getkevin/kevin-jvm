package eu.kevin.api.models.payment.getPaymentStatus

import eu.kevin.api.models.ErrorCode
import kotlinx.serialization.Serializable

@Serializable
data class GetPaymentStatusResponseError(
    val code: ErrorCode
)
