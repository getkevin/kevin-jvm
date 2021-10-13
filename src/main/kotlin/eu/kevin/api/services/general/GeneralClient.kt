package eu.kevin.api.services.general

import eu.kevin.api.Endpoint
import eu.kevin.api.models.ResponseArray
import eu.kevin.api.models.general.bank.BankResponse
import eu.kevin.api.models.general.getProjectSettings.GetProjectSettingsResponse
import io.ktor.client.*
import io.ktor.client.request.*

class GeneralClient internal constructor(
    private val httpClient: HttpClient
) {
    suspend fun getSupportedCountries(): List<String> =
        httpClient.get<ResponseArray<String>>(
            path = Endpoint.Path.General.getSupportedCountries()
        ).data

    @JvmOverloads
    suspend fun getSupportedBanks(countryCode: String? = null): List<BankResponse> =
        httpClient.get<ResponseArray<BankResponse>>(
            path = Endpoint.Path.General.getSupportedBanks()
        ) {
            parameter("countryCode", countryCode)
        }.data

    suspend fun getSupportedBank(bankId: String): BankResponse =
        httpClient.get(
            path = Endpoint.Path.General.getSupportedBank(bankId = bankId)
        )

    suspend fun getSupportedBankByCardNumberPiece(cardNumberPiece: String): BankResponse =
        httpClient.get(
            path = Endpoint.Path.General.getSupportedBankByCardNumberPiece(cardNumberPiece = cardNumberPiece)
        )

    suspend fun getPaymentMethods(): List<String> =
        httpClient.get<ResponseArray<String>>(
            path = Endpoint.Path.General.getPaymentMethods()
        ).data

    suspend fun getProjectSettings(): GetProjectSettingsResponse =
        httpClient.get(
            path = Endpoint.Path.General.getProjectSettings()
        )
}