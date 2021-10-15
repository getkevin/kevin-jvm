package eu.kevin.api.models.account.balance.request

import eu.kevin.api.models.account.AccountRequest
import eu.kevin.api.models.account.PsuHttpMethod

data class GetAccountBalanceRequest(
    val accountId: String,
    override val accessToken: String,
    override val psuDeviceId: String,
    override val psuIPAddress: String,
    override val psuUserAgent: String,
    override val psuIPPort: String,
    override val psuHttpMethod: PsuHttpMethod,
) : AccountRequest
