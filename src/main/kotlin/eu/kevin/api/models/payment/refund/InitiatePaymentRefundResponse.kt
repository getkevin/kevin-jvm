@file:UseSerializers(LocalDateTimeSerializer::class, BigDecimalSerializer::class)

package eu.kevin.api.models.payment.refund

import eu.kevin.api.models.payment.StatusGroup
import eu.kevin.api.serializers.BigDecimalSerializer
import eu.kevin.api.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class InitiatePaymentRefundResponse(
    val id: Int,
    val paymentId: String,
    val amount: BigDecimal,
    val statusGroup: StatusGroup,
    val webhookUrl: String,
    val createdAt: LocalDateTime
)
