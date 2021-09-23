package eu.kevin.api.models.payment.initiatePayment.request

import java.math.BigDecimal

data class InitiatePaymentRequest @JvmOverloads constructor(
    val redirectUrl: String,
    val amount: BigDecimal,
    val currencyCode: String,
    val description: String,
    var bankPaymentMethod: BankPaymentMethod? = null,
    var cardPaymentMethod: CardPaymentMethod? = null,
    var identifier: UserIdentifier? = null,
    var accessToken: String? = null,
    var bankId: String? = null,
    var redirectPreferred: Boolean? = null,
    var paymentMethodPreferred: PaymentMethod? = null,
    var webhookUrl: String? = null,
)
