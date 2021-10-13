package eu.kevin.api.models.general.bank

import kotlinx.serialization.Serializable

@Serializable
data class BankResponse(
    val id: String,
    val name: String,
    val officialName: String? = null,
    val scaApproaches: ScaApproaches,
    val countryCode: String,
    val isSandbox: Boolean,
    val imageUri: String? = null,
    val bic: String,
    val isBeta: Boolean
)
