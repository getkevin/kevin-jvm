package eu.kevin.api.payments.initiatePayment.response

import kotlinx.serialization.Serializable

@Serializable
enum class BankStatus {
    STRD,
    ACCC,
    ACCP,
    ACSC,
    ACSP,
    ACTC,
    ACWC,
    ACWP,
    RCVD,
    PDNG,
    RJCT,
    CANC,
    ACFC,
    PATC,
}