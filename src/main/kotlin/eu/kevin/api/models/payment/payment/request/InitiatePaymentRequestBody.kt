@file:UseSerializers(BigDecimalSerializer::class)

package eu.kevin.api.models.payment.payment.request

import eu.kevin.api.serializers.BigDecimalSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal

@Serializable
internal data class InitiatePaymentRequestBody(
    val amount: BigDecimal,
    val currencyCode: String,
    val description: String,
    val bankPaymentMethod: BankPaymentMethod? = null,
    val cardPaymentMethod: CardPaymentMethod? = null,
    val identifier: UserIdentifier? = null
)
