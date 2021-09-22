@file:UseSerializers(BigDecimalSerializer::class)

package eu.kevin.api.models.payment.initiatePayment.request

import eu.kevin.api.serializers.BigDecimalSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal

@Serializable
data class InitiatePaymentRequest @JvmOverloads constructor(
    val redirectUrl: String,
    val amount: BigDecimal,
    val currencyCode: String,
    val description: String,
    var bankPaymentMethod: BankPaymentMethod,
    var cardPaymentMethod: CardPaymentMethod? = null,
    var identifier: UserIdentifier? = null,
    var accessToken: String? = null,
    var bankId: String? = null,
    var redirectPreferred: Boolean? = null,
    var paymentMethodPreferred: PaymentMethod? = null,
    var webhookUrl: String? = null,
)
