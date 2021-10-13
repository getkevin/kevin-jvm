package eu.kevin.api.models.general.getProjectSettings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusSettings(
    @SerialName("isACSPCompletedStatus") val isAcspCompletedStatus: Boolean
)
