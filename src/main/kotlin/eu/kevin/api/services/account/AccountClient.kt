package eu.kevin.api.services.account

import eu.kevin.api.Endpoint
import eu.kevin.api.exceptions.KevinApiErrorException
import eu.kevin.api.models.ResponseArray
import eu.kevin.api.models.account.AccountRequestHeaders
import eu.kevin.api.models.account.balance.request.GetAccountBalanceRequest
import eu.kevin.api.models.account.balance.response.AccountBalanceResponse
import eu.kevin.api.models.account.detail.AccountDetailsResponse
import eu.kevin.api.models.account.detail.GetAccountDetailsRequest
import eu.kevin.api.models.account.list.AccountResponse
import eu.kevin.api.models.account.transaction.request.GetAccountTransactionsRequest
import eu.kevin.api.models.account.transaction.response.AccountTransactionResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

/**
 * Implements API Methods of the [Account information service](https://docs.kevin.eu/public/platform/v0.3#tag/Account-Information-Service)
 */
class AccountClient internal constructor(
    private val httpClient: HttpClient,
    private val serializer: Json
) {

    /**
     *  API Method: [Get accounts list](https://docs.kevin.eu/public/platform/v0.3#operation/getAccounts)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getAccountsList(request: AccountRequestHeaders): List<AccountResponse> =
        httpClient.get<ResponseArray<AccountResponse>>(
            path = Endpoint.Paths.Account.getAccountsList()
        ) {
            appendAccountRequestHeaders(headers = request)
        }.data

    /**
     * API Method: [Get account details](https://docs.kevin.eu/public/platform/v0.3#operation/getAccount)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getAccountDetails(request: GetAccountDetailsRequest): AccountDetailsResponse =
        httpClient.get(
            path = Endpoint.Paths.Account.getAccountDetails(accountId = request.accountId)
        ) {
            appendAccountRequestHeaders(headers = request.headers)
        }

    /**
     * API Method: [Get account transactions](https://docs.kevin.eu/public/platform/v0.3#operation/getAccountTransactions)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getAccountTransactions(request: GetAccountTransactionsRequest): List<AccountTransactionResponse> =
        httpClient.get<ResponseArray<AccountTransactionResponse>>(
            path = Endpoint.Paths.Account.getAccountTransactions(accountId = request.accountId)
        ) {
            appendAccountRequestHeaders(headers = request.headers)
            parameter("dateFrom", request.dateFrom)
            parameter("dateTo", request.dateTo)
        }.data

    /**
     * API Method: [Get account balance](https://docs.kevin.eu/public/platform/v0.3#operation/getAccountBalance)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getAccountBalances(request: GetAccountBalanceRequest): List<AccountBalanceResponse> =
        httpClient.get<ResponseArray<AccountBalanceResponse>>(
            path = Endpoint.Paths.Account.getAccountBalance(accountId = request.accountId)
        ) {
            appendAccountRequestHeaders(headers = request.headers)
        }.data

    private fun HttpRequestBuilder.appendAccountRequestHeaders(headers: AccountRequestHeaders) {
        headers.run {
            headers {
                append("Authorization", "Bearer $accessToken")
                append("PSU-IP-Address", psuIPAddress)
                append("PSU-User-Agent", psuUserAgent)
                append("PSU-IP-Port", psuIPPort)
                append("PSU-Http-Method", psuHttpMethod.value)
                append("PSU-Device-ID", psuDeviceId)
            }
        }
    }
}
