package eu.kevin.api.models.payment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class StatusGroup {
    @SerialName("created") CREATED,
    @SerialName("started") STARTED,
    @SerialName("pending") PENDING,
    @SerialName("completed") COMPLETED,
    @SerialName("failed") FAILED,
}
