@file:UseSerializers(BigDecimalSerializer::class)

package eu.kevin.api.models.payment.refund

import eu.kevin.api.serializers.BigDecimalSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal

@Serializable
internal data class InitiatePaymentRefundRequestBody(
    val amount: BigDecimal
)
