package eu.kevin.api.services.general

import eu.kevin.api.Endpoint
import eu.kevin.api.exceptions.KevinApiErrorException
import eu.kevin.api.models.ResponseArray
import eu.kevin.api.models.general.bank.BankResponse
import eu.kevin.api.models.general.projectSettings.GetProjectSettingsResponse
import io.ktor.client.*
import io.ktor.client.request.*

/**
 * Implements API Methods of the [General service](https://docs.kevin.eu/public/platform/v0.3#tag/General-service)
 */
class GeneralClient internal constructor(
    private val httpClient: HttpClient
) {
    /**
     * API Method: [Get supported countries](https://docs.kevin.eu/public/platform/v0.3#operation/getCountries)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getSupportedCountries(): List<String> =
        httpClient.get<ResponseArray<String>>(
            path = Endpoint.Paths.General.getSupportedCountries()
        ).data

    /**
     * API Method: [Get supported banks](https://docs.kevin.eu/public/platform/v0.3#operation/getBanks)
     */
    @Throws(KevinApiErrorException::class)
    @JvmOverloads
    suspend fun getSupportedBanks(countryCode: String? = null): List<BankResponse> =
        httpClient.get<ResponseArray<BankResponse>>(
            path = Endpoint.Paths.General.getSupportedBanks()
        ) {
            parameter("countryCode", countryCode)
        }.data

    /**
     * API Method: [Get supported bank](https://docs.kevin.eu/public/platform/v0.3#operation/getBank)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getSupportedBank(bankId: String): BankResponse =
        httpClient.get(
            path = Endpoint.Paths.General.getSupportedBank(bankId = bankId)
        )

    /**
     * API Method: [Get supported bank by card number piece](https://docs.kevin.eu/public/platform/v0.3#operation/getBankByCardNumberPiece)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getSupportedBankByCardNumberPiece(cardNumberPiece: String): BankResponse =
        httpClient.get(
            path = Endpoint.Paths.General.getSupportedBankByCardNumberPiece(cardNumberPiece = cardNumberPiece)
        )

    /**
     * API Method: [Get payment methods](https://docs.kevin.eu/public/platform/v0.3#operation/getPaymentMethods)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getPaymentMethods(): List<String> =
        httpClient.get<ResponseArray<String>>(
            path = Endpoint.Paths.General.getPaymentMethods()
        ).data

    /**
     * API Method: [Get project settings](https://docs.kevin.eu/public/platform/v0.3#operation/getProjectSettings)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getProjectSettings(): GetProjectSettingsResponse =
        httpClient.get(
            path = Endpoint.Paths.General.getProjectSettings()
        )
}
