package eu.kevin.api.models.account.list

import kotlinx.serialization.Serializable

@Serializable
data class AccountResponse(
    val id: String? = null,
    val iban: String,
    val bban: String? = null,
    val paym: String? = null,
    val pan: String? = null,
    val sortCodeAccountNumber: String? = null,
    val type: String? = null,
    val currencyCode: String,
    val name: String? = null
)
