package eu.kevin.api.models.account.balance.request

import eu.kevin.api.models.account.AccountRequestHeaders

data class GetAccountBalanceRequest(
    val accountId: String,
    val headers: AccountRequestHeaders
)
