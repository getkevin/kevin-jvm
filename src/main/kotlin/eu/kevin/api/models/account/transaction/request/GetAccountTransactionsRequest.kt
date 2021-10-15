package eu.kevin.api.models.account.transaction.request

import eu.kevin.api.models.account.AccountRequest
import eu.kevin.api.models.account.PsuHttpMethod
import java.time.LocalDate

data class GetAccountTransactionsRequest(
    val accountId: String,
    val dateFrom: LocalDate,
    val dateTo: LocalDate,
    override val accessToken: String,
    override val psuDeviceId: String,
    override val psuIPAddress: String,
    override val psuUserAgent: String,
    override val psuIPPort: String,
    override val psuHttpMethod: PsuHttpMethod,
) : AccountRequest
