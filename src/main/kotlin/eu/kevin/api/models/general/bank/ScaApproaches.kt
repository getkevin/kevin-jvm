package eu.kevin.api.models.general.bank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScaApproaches(
    @SerialName("AIS") val ais: List<ScaApproachType>,
    @SerialName("PIS") val pis: List<ScaApproachType>,
    @SerialName("AUTH") val auth: List<ScaApproachType>
)
