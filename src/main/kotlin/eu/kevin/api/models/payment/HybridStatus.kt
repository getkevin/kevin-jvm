package eu.kevin.api.models.payment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class HybridStatus {
    @SerialName("created")
    CREATED,
    @SerialName("canceled")
    CANCELED,
    @SerialName("expired")
    EXPIRED,
}
