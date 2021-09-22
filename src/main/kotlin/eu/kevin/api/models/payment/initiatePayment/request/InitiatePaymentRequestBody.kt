@file:UseSerializers(BigDecimalSerializer::class)

package eu.kevin.api.models.payment.initiatePayment.request

import eu.kevin.api.serializers.BigDecimalSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal

@Serializable
data class InitiatePaymentRequestBody @JvmOverloads constructor(
    val amount: BigDecimal,
    val currencyCode: String,
    val description: String,
    var bankPaymentMethod: BankPaymentMethod,
    var cardPaymentMethod: CardPaymentMethod? = null,
    var identifier: UserIdentifier? = null
)
