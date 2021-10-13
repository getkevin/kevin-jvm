package eu.kevin.api.models.general.bank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ScaApproachType {
    @SerialName("REDIRECT") REDIRECT,
    @SerialName("DECOUPLED") DECOUPLED,
    @SerialName("EMBEDDED") EMBEDDED
}