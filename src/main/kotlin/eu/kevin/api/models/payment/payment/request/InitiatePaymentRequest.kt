package eu.kevin.api.models.payment.payment.request

import java.math.BigDecimal

open class InitiatePaymentRequest @JvmOverloads constructor(
    open val redirectUrl: String,
    open val amount: BigDecimal,
    open val currencyCode: String,
    open val description: String,
    open var bankPaymentMethod: BankPaymentMethod? = null,
    open var cardPaymentMethod: CardPaymentMethod? = null,
    open var identifier: UserIdentifier? = null,
    open var accessToken: String? = null,
    open var bankId: String? = null,
    open var redirectPreferred: Boolean? = null,
    open var paymentMethodPreferred: PaymentMethod? = null,
    open var webhookUrl: String? = null,
    open var lang: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InitiatePaymentRequest

        if (redirectUrl != other.redirectUrl) return false
        if (amount != other.amount) return false
        if (currencyCode != other.currencyCode) return false
        if (description != other.description) return false
        if (bankPaymentMethod != other.bankPaymentMethod) return false
        if (cardPaymentMethod != other.cardPaymentMethod) return false
        if (identifier != other.identifier) return false
        if (accessToken != other.accessToken) return false
        if (bankId != other.bankId) return false
        if (redirectPreferred != other.redirectPreferred) return false
        if (paymentMethodPreferred != other.paymentMethodPreferred) return false
        if (webhookUrl != other.webhookUrl) return false
        if (lang != other.lang) return false

        return true
    }

    override fun hashCode(): Int {
        var result = redirectUrl.hashCode()
        result = 31 * result + amount.hashCode()
        result = 31 * result + currencyCode.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + (bankPaymentMethod?.hashCode() ?: 0)
        result = 31 * result + (cardPaymentMethod?.hashCode() ?: 0)
        result = 31 * result + (identifier?.hashCode() ?: 0)
        result = 31 * result + (accessToken?.hashCode() ?: 0)
        result = 31 * result + (bankId?.hashCode() ?: 0)
        result = 31 * result + (redirectPreferred?.hashCode() ?: 0)
        result = 31 * result + (paymentMethodPreferred?.hashCode() ?: 0)
        result = 31 * result + (webhookUrl?.hashCode() ?: 0)
        result = 31 * result + (lang?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "InitiatePaymentRequest(redirectUrl='$redirectUrl', amount=$amount, currencyCode='$currencyCode', description='$description', bankPaymentMethod=$bankPaymentMethod, cardPaymentMethod=$cardPaymentMethod, identifier=$identifier, accessToken=$accessToken, bankId=$bankId, redirectPreferred=$redirectPreferred, paymentMethodPreferred=$paymentMethodPreferred, webhookUrl=$webhookUrl, lang=$lang)"
    }
}
