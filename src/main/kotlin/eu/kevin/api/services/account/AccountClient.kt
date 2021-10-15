package eu.kevin.api.services.account

import eu.kevin.api.Endpoint
import eu.kevin.api.models.ResponseArray
import eu.kevin.api.models.account.AccountRequest
import eu.kevin.api.models.account.balance.request.GetAccountBalanceRequest
import eu.kevin.api.models.account.balance.response.AccountBalanceResponse
import eu.kevin.api.models.account.detail.AccountDetailsResponse
import eu.kevin.api.models.account.detail.GetAccountDetailsRequest
import eu.kevin.api.models.account.list.AccountResponse
import eu.kevin.api.models.account.list.GetAccountsListRequest
import eu.kevin.api.models.account.transaction.request.GetAccountTransactionsRequest
import eu.kevin.api.models.account.transaction.response.AccountTransactionResponse
import eu.kevin.api.serializers.LocalDateSerializer
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class AccountClient internal constructor(
    private val httpClient: HttpClient,
    private val serializer: Json
) {
    suspend fun getAccountsList(request: GetAccountsListRequest): List<AccountResponse> =
        httpClient.get<ResponseArray<AccountResponse>>(
            path = Endpoint.Paths.Account.getAccountsList()
        ) {
            appendAccountRequestHeaders(request = request)
        }.data

    suspend fun getAccountDetails(request: GetAccountDetailsRequest): AccountDetailsResponse =
        httpClient.get(
            path = Endpoint.Paths.Account.getAccountDetails(accountId = request.accountId)
        ) {
            appendAccountRequestHeaders(request = request)
        }

    suspend fun getAccountTransactions(request: GetAccountTransactionsRequest): List<AccountTransactionResponse> =
        httpClient.get<ResponseArray<AccountTransactionResponse>>(
            path = Endpoint.Paths.Account.getAccountTransactions(accountId = request.accountId)
        ) {
            appendAccountRequestHeaders(request = request)
            parameter("dateFrom", serializer.encodeToString(LocalDateSerializer, request.dateFrom))
            parameter("dateTo", serializer.encodeToString(LocalDateSerializer, request.dateTo))
        }.data

    suspend fun getAccountBalances(request: GetAccountBalanceRequest): List<AccountBalanceResponse> =
        httpClient.get<ResponseArray<AccountBalanceResponse>>(
            path = Endpoint.Paths.Account.getAccountBalance(accountId = request.accountId)
        ) {
            appendAccountRequestHeaders(request = request)
        }.data

    private fun HttpRequestBuilder.appendAccountRequestHeaders(request: AccountRequest) {
        request.run {
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