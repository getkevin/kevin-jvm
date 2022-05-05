package eu.kevin.api.models.general.projectSettings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetProjectSettingsResponse(
    val scopes: List<String>,
    val isSandbox: Boolean,
    val paymentMethods: List<String>,
    val allowedRefundsFor: List<String>? = null,
    val forceRedirectToBankMode: ForceRedirectToBankMode,
    val cardRefundDayLimit: Int,
    val bankRefundDayLimit: Int,
    val redirectPreferred: Boolean,
    @SerialName("skipPaymentSCA") val skipPaymentSca: Boolean,
    val webhookDelayInSec: Int,
    val paymentExpiresTmoInSec: Int,
    val statusSettings: StatusSettings
)
