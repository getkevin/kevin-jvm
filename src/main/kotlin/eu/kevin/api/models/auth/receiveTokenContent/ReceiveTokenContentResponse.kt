package eu.kevin.api.models.auth.receiveTokenContent

import kotlinx.serialization.Serializable

@Serializable
data class ReceiveTokenContentResponse(
    val clientConsent: Boolean,
    val expiresIn: Long,
    val bankName: String,
    val bankId: String
)
