package eu.kevin.api.payments.initiatePayment.request

import kotlinx.serialization.Serializable

@Serializable
data class InformationStructured @JvmOverloads constructor(
    val reference: String,
    var referenceType: String? = null
)
