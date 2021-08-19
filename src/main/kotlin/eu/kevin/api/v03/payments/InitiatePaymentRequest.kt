package eu.kevin.api.v03.payments

import java.math.BigDecimal
import java.time.LocalDate

data class InitiatePaymentRequest @JvmOverloads constructor(
    val redirectUrl: String,
    val paymentData: InitiatePaymentRequestBody,
    var accessToken: String? = null,
    var bankId: String? = null,
    var redirectPreferred: Boolean? = null,
    var paymentMethodPreferred: PaymentMethod? = null,
    var webhookUrl: String? = null,
)

enum class PaymentMethod(val title: String) {
    Bank("bank"),
    Card("card")
}

data class UserIdentifier(
    val email: String
)

data class CardPaymentMethod @JvmOverloads constructor(
    var cvc: String? = null,
    var expMonth: Int? = null,
    var expYear: Int? = null,
    var number: String? = null,
    var holderName: String? = null
)

data class InformationStructured @JvmOverloads constructor(
    val reference: String,
    var referenceType: String? = null
)

data class Account @JvmOverloads constructor(
    val iban: String,
    var bban: String? = null,
    var sortCodeAccountNumber: String? = null,
    var currencyCode: String? = null
)

data class BankPaymentMethod @JvmOverloads constructor(
    val endToEndId: String,
    val creditorName: String,
    val creditorAccount: Account,
    var debtorAccount: Account? = null,
    var requestedExecutionDate: LocalDate? = null,
    var informationStructured: InformationStructured? = null,
)

data class InitiatePaymentRequestBody @JvmOverloads constructor(
    val amount: BigDecimal,
    val currencyCode: String,
    val description: String,
    var bankPaymentMethod: BankPaymentMethod,
    var cardPaymentMethod: CardPaymentMethod? = null,
    var identifier: UserIdentifier? = null
)
