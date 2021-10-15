package eu.kevin.api.models.account.transaction.response

import kotlinx.serialization.Serializable

@Serializable
data class CounterPartyAccount(
    val iban: String,
    val bban: String? = null,
    val pan: String? = null,
    val sortCodeAccountNumber: String? = null
)
