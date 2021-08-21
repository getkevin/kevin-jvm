package eu.kevin.api.payments.initiatePayment.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class StatusGroup {
    @SerialName("started") STARTED,
    @SerialName("pending") PENDING,
    @SerialName("completed") COMPLETED,
    @SerialName("failed") FAILED,
}
