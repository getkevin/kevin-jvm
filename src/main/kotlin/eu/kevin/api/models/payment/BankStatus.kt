package eu.kevin.api.models.payment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BankStatus(val value: String) {
    @SerialName("STRD") STARTED("STRD"),
    @SerialName("ACCC") ACCEPTED_SETTLEMENT_COMPLETED_CREDITOR("ACCC"),
    @SerialName("ACCP") ACCEPTED_CUSTOMER_PROFILE("ACCP"),
    @SerialName("ACSC") ACCEPTED_SETTLEMENT_COMPLETED_DEBTOR("ACSC"),
    @SerialName("ACSP") ACCEPTED_SETTLEMENT_IN_PROCESS("ACSP"),
    @SerialName("ACTC") ACCEPTED_TECHNICAL_VALIDATION("ACTC"),
    @SerialName("ACWC") ACCEPTED_WITH_CHANGE("ACWC"),
    @SerialName("ACWP") ACCEPTED_WITHOUT_POSTING("ACWP"),
    @SerialName("RCVD") RECEIVED("RCVD"),
    @SerialName("PDNG") PENDING("PDNG"),
    @SerialName("RJCT") REJECTED("RJCT"),
    @SerialName("CANC") CANCELLED("CANC"),
    @SerialName("ACFC") ACCEPTED_FUNDS_CHECKED("ACFC"),
    @SerialName("PATC") PARTIALLY_ACCEPTED_TECHNICAL("PATC"),
}