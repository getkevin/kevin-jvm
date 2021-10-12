@file:UseSerializers(LocalDateTimeSerializer::class)

package eu.kevin.api.models.payment.initiatePaymentRefund

import eu.kevin.api.models.payment.StatusGroup
import eu.kevin.api.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDateTime

@Serializable
data class InitiatePaymentRefundResponse(
    val id: Int,
    val paymentId: String,
    val amount: String,
    val statusGroup: StatusGroup,
    val webhookUrl: String,
    val createdAt: LocalDateTime
)
