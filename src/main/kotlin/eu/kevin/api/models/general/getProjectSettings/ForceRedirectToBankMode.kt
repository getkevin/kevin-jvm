package eu.kevin.api.models.general.getProjectSettings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ForceRedirectToBankMode {
    @SerialName("soft") SOFT,
    @SerialName("hard") HARD,
    @SerialName("none") NONE
}