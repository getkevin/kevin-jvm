package eu.kevin.api.models.payment.payment.request

import java.math.BigDecimal

data class InitiateHybridPaymentRequest @JvmOverloads constructor(
    override val redirectUrl: String,
    override val amount: BigDecimal,
    override val currencyCode: String,
    override val description: String,
    val mandatoryBankPaymentMethod: BankPaymentMethod,
    val mandatoryCardPaymentMethod: CardPaymentMethod,
    override var identifier: UserIdentifier? = null,
    override var accessToken: String? = null,
    override var bankId: String? = null,
    override var redirectPreferred: Boolean? = null,
    override var paymentMethodPreferred: PaymentMethod? = null,
    override var webhookUrl: String? = null,
    override var lang: String? = null
) : InitiatePaymentRequest(
    redirectUrl,
    amount,
    currencyCode,
    description,
    bankPaymentMethod = mandatoryBankPaymentMethod,
    cardPaymentMethod = mandatoryCardPaymentMethod,
    identifier,
    accessToken,
    bankId,
    redirectPreferred,
    paymentMethodPreferred,
    webhookUrl,
    lang
)
