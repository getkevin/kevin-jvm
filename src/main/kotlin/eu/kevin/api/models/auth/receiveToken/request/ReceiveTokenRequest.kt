package eu.kevin.api.models.auth.receiveToken.request

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class ReceiveTokenRequest(
    val code: String
) {
    @Required
    private val grantType = AuthGrantType.AUTHORIZATION_CODE
}
