package eu.kevin.api.models.payment.initiatePayment.request

import kotlinx.serialization.Serializable

@Serializable
data class InformationStructured @JvmOverloads constructor(
    val reference: String,
    var referenceType: String? = null
)
