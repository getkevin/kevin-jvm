package eu.kevin.api.models.account.detail

import eu.kevin.api.models.account.AccountRequestHeaders

data class GetAccountDetailsRequest(
    val accountId: String,
    val headers: AccountRequestHeaders
)
