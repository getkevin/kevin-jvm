package eu.kevin.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ErrorCode(val code: Int) {
    @SerialName("10000") SYSTEM_ERROR(10000),
    @SerialName("20017") INSUFFICIENT_FUNDS(20017),
    @SerialName("20022") CREDITOR_ACCOUNT_NUMBER_INVALID_OR_MISSING(20022),
    @SerialName("20023") THE_ACCOUNT_NUMBER_AND_THE_NAME_DO_NOT_COINCIDE(20023),
    @SerialName("20024") TRANSACTION_CURRENCY_IS_INVALID_OR_MISSING(20024),
    @SerialName("20029") REMITTANCE_INFORMATION_IS_NOT_VALID(20029),
    @SerialName("20032") AUTHORIZATION_HEADER_IS_MISSING_OR_INVALID(20032)
}
