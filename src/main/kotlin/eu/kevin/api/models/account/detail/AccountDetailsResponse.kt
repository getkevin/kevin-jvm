package eu.kevin.api.models.account.detail

import kotlinx.serialization.Serializable

@Serializable
data class AccountDetailsResponse(
    val iban: String,
    val type: String? = null,
    val currencyCode: String,
    val name: String,
    val status: String
)
