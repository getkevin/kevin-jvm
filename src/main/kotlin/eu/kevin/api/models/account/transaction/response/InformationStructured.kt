package eu.kevin.api.models.account.transaction.response

import kotlinx.serialization.Serializable

@Serializable
data class InformationStructured(
    val type: String? = null,
    val reference: String? = null
)
