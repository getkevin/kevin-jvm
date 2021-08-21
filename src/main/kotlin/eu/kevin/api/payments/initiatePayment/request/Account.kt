package eu.kevin.api.payments.initiatePayment.request

import kotlinx.serialization.Serializable

@Serializable
data class Account @JvmOverloads constructor(
    val iban: String,
    var bban: String? = null,
    var sortCodeAccountNumber: String? = null,
    var currencyCode: String? = null
)
