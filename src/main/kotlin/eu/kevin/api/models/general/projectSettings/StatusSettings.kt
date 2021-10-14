package eu.kevin.api.models.general.projectSettings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusSettings(
    @SerialName("isACSPCompletedStatus") val isAcspCompletedStatus: Boolean
)
