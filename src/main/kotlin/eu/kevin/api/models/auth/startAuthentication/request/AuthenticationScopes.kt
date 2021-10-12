package eu.kevin.api.models.auth.startAuthentication.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AuthenticationScopes {
    @SerialName("payments") PAYMENTS,
    @SerialName("accounts_details") ACCOUNTS_DETAILS,
    @SerialName("accounts_balances") ACCOUNTS_BALANCES,
    @SerialName("accounts_transactions") ACCOUNTS_TRANSACTIONS,
    @SerialName("accounts_basic") ACCOUNTS_BASIC
}
