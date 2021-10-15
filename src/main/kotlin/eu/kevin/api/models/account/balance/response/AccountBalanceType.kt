package eu.kevin.api.models.account.balance.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AccountBalanceType {
    @SerialName("closingBooked") CLOSING_BOOKED,
    @SerialName("expected") EXPECTED,
    @SerialName("authorised") AUTHORISED,
    @SerialName("openingBooked") OPENING_BOOKED,
    @SerialName("interimAvailable") INTERIM_AVAILABLE,
    @SerialName("interimBooked") INTERIM_BOOKED,
    @SerialName("forwardAvailable") FORWARD_AVAILABLE,
    @SerialName("nonInvoiced") NON_INVOICED
}