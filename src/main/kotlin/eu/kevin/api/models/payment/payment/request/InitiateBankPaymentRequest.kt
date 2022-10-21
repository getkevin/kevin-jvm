package eu.kevin.api.models.payment.payment.request

import java.math.BigDecimal

data class InitiateBankPaymentRequest @JvmOverloads constructor(
    override val redirectUrl: String,
    override val amount: BigDecimal,
    override val currencyCode: String,
    override val description: String,
    val mandatoryBankPaymentMethod: BankPaymentMethod,
    override var identifier: UserIdentifier? = null,
    override var accessToken: String? = null,
    override var bankId: String? = null,
    override var redirectPreferred: Boolean? = null,
    override var webhookUrl: String? = null,
    override var lang: String? = null
) : InitiatePaymentRequest(
    redirectUrl,
    amount,
    currencyCode,
    description,
    bankPaymentMethod = mandatoryBankPaymentMethod,
    cardPaymentMethod = null,
    identifier,
    accessToken,
    bankId,
    redirectPreferred,
    paymentMethodPreferred = PaymentMethod.BANK,
    webhookUrl,
    lang
)
