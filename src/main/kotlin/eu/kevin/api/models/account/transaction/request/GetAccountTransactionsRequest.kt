package eu.kevin.api.models.account.transaction.request

import eu.kevin.api.models.account.AccountRequestHeaders
import java.time.LocalDate

data class GetAccountTransactionsRequest(
    val accountId: String,
    val dateFrom: LocalDate,
    val dateTo: LocalDate,
    val headers: AccountRequestHeaders
)
