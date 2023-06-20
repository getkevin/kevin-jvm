package eu.kevin.api.services.account

import eu.kevin.api.Endpoint
import eu.kevin.api.exceptions.KevinApiErrorException
import eu.kevin.api.extensions.appendAtStartIfNotExist
import eu.kevin.api.extensions.suspendingToCompletableFuture
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
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.util.concurrent.CompletableFuture

/**
 * Implements API Methods of the [Account information service](https://api-reference.kevin.eu/public/platform/v0.3#tag/Account-Information-Service)
 */
class AccountClient internal constructor(
    private val httpClient: HttpClient
) {

    /**
     *  API Method: [Get accounts list](https://api-reference.kevin.eu/public/platform/v0.3#tag/Account-Information-Service/operation/getAccounts)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getAccountsList(request: AccountRequestHeaders): List<AccountResponse> =
        httpClient.get {
            url(path = Endpoint.Paths.Account.getAccountsList())
            appendAccountRequestHeaders(headers = request)
        }.body<ResponseArray<AccountResponse>>().data

    /**
     * Equivalent of suspending `getAccountsList(request: AccountRequestHeaders)` for Java interoperability
     */
    @JvmName("getAccountsList")
    fun getAccountsListAsFuture(request: AccountRequestHeaders): CompletableFuture<List<AccountResponse>> {
        return suspendingToCompletableFuture { getAccountsList(request) }
    }

    /**
     * API Method: [Get account details](https://api-reference.kevin.eu/public/platform/v0.3#tag/Account-Information-Service/operation/getAccount)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getAccountDetails(request: GetAccountDetailsRequest): AccountDetailsResponse =
        httpClient.get {
            url(path = Endpoint.Paths.Account.getAccountDetails(accountId = request.accountId))
            appendAccountRequestHeaders(headers = request.headers)
        }.body()

    /**
     * Equivalent of suspending `getAccountDetails(request: GetAccountDetailsRequest)` for Java interoperability
     */
    @JvmName("getAccountDetails")
    fun getAccountDetailsAsFuture(request: GetAccountDetailsRequest): CompletableFuture<AccountDetailsResponse> {
        return suspendingToCompletableFuture { getAccountDetails(request) }
    }

    /**
     * API Method: [Get account transactions](https://api-reference.kevin.eu/public/platform/v0.3#tag/Account-Information-Service/operation/getAccountTransactions)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getAccountTransactions(request: GetAccountTransactionsRequest): List<AccountTransactionResponse> =
        httpClient.get {
            url(path = Endpoint.Paths.Account.getAccountTransactions(accountId = request.accountId))
            appendAccountRequestHeaders(headers = request.headers)
            parameter("dateFrom", request.dateFrom)
            parameter("dateTo", request.dateTo)
        }.body<ResponseArray<AccountTransactionResponse>>().data

    /**
     * Equivalent of suspending `getAccountTransactions(request: GetAccountTransactionsRequest)` for Java interoperability
     */
    @JvmName("getAccountTransactions")
    fun getAccountTransactionsAsFuture(request: GetAccountTransactionsRequest): CompletableFuture<List<AccountTransactionResponse>> {
        return suspendingToCompletableFuture { getAccountTransactions(request) }
    }

    /**
     * API Method: [Get account balance](https://api-reference.kevin.eu/public/platform/v0.3#tag/Account-Information-Service/operation/getAccountBalance)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getAccountBalances(request: GetAccountBalanceRequest): List<AccountBalanceResponse> =
        httpClient.get {
            url(path = Endpoint.Paths.Account.getAccountBalance(accountId = request.accountId))
            appendAccountRequestHeaders(headers = request.headers)
        }.body<ResponseArray<AccountBalanceResponse>>().data

    /**
     * Equivalent of suspending `getAccountBalances(request: GetAccountBalanceRequest)` for Java interoperability
     */
    @JvmName("getAccountBalances")
    fun getAccountBalancesAsFuture(request: GetAccountBalanceRequest): CompletableFuture<List<AccountBalanceResponse>> {
        return suspendingToCompletableFuture { getAccountBalances(request) }
    }

    private fun HttpRequestBuilder.appendAccountRequestHeaders(headers: AccountRequestHeaders) {
        headers.run {
            headers {
                append("Authorization", accessToken.appendAtStartIfNotExist("Bearer "))
                append("PSU-IP-Address", psuIPAddress)
                append("PSU-User-Agent", psuUserAgent)
                append("PSU-IP-Port", psuIPPort)
                append("PSU-Http-Method", psuHttpMethod.value)
                append("PSU-Device-ID", psuDeviceId)
            }
        }
    }
}
