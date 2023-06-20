package eu.kevin.api.services.general

import eu.kevin.api.Endpoint
import eu.kevin.api.exceptions.KevinApiErrorException
import eu.kevin.api.extensions.suspendingToCompletableFuture
import eu.kevin.api.models.ResponseArray
import eu.kevin.api.models.general.bank.BankResponse
import eu.kevin.api.models.general.projectSettings.GetProjectSettingsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.util.concurrent.CompletableFuture

/**
 * Implements API Methods of the [General service](https://api-reference.kevin.eu/public/platform/v0.3#tag/General-Service)
 */
class GeneralClient internal constructor(
    private val httpClient: HttpClient
) {
    /**
     * API Method: [Get supported countries](https://api-reference.kevin.eu/public/platform/v0.3#tag/General-Service/operation/getCountries)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getSupportedCountries(): List<String> =
        httpClient.get {
            url(path = Endpoint.Paths.General.getSupportedCountries())
        }.body<ResponseArray<String>>().data

    /**
     * Equivalent of suspending `getSupportedCountries()` for Java interoperability
     */
    @JvmName("getSupportedCountries")
    fun getSupportedCountriesAsFuture(): CompletableFuture<List<String>> {
        return suspendingToCompletableFuture { getSupportedCountries() }
    }

    /**
     * API Method: [Get supported banks](https://api-reference.kevin.eu/public/platform/v0.3#tag/General-Service/operation/getBanks)
     */
    @Throws(KevinApiErrorException::class)
    @JvmOverloads
    suspend fun getSupportedBanks(countryCode: String? = null): List<BankResponse> =
        httpClient.get {
            url(path = Endpoint.Paths.General.getSupportedBanks())
            parameter("countryCode", countryCode)
        }.body<ResponseArray<BankResponse>>().data

    /**
     * Equivalent of suspending `getSupportedBanks(countryCode: String?)` for Java interoperability
     */
    @JvmName("getSupportedBanks")
    fun getSupportedBanksAsFuture(countryCode: String? = null): CompletableFuture<List<BankResponse>> {
        return suspendingToCompletableFuture { getSupportedBanks(countryCode) }
    }

    /**
     * API Method: [Get supported bank](https://api-reference.kevin.eu/public/platform/v0.3#tag/General-Service/operation/getBank)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getSupportedBank(bankId: String): BankResponse =
        httpClient.get {
            url(path = Endpoint.Paths.General.getSupportedBank(bankId = bankId))
        }.body()

    /**
     * Equivalent of suspending `getSupportedBank(bankId: String)` for Java interoperability
     */
    @JvmName("getSupportedBank")
    fun getSupportedBankAsFuture(bankId: String): CompletableFuture<BankResponse> {
        return suspendingToCompletableFuture { getSupportedBank(bankId) }
    }

    /**
     * API Method: [Get supported bank by card number piece](https://api-reference.kevin.eu/public/platform/v0.3#tag/General-Service/operation/getBankByCardNumberPiece)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getSupportedBankByCardNumberPiece(cardNumberPiece: String): BankResponse =
        httpClient.get {
            url(path = Endpoint.Paths.General.getSupportedBankByCardNumberPiece(cardNumberPiece = cardNumberPiece))
        }.body()

    /**
     * Equivalent of suspending `getSupportedBankByCardNumberPiece(cardNumberPiece: String)` for Java interoperability
     */
    @JvmName("getSupportedBankByCardNumberPiece")
    fun getSupportedBankByCardNumberPieceAsFuture(cardNumberPiece: String): CompletableFuture<Unit> {
        return suspendingToCompletableFuture { getSupportedBankByCardNumberPiece(cardNumberPiece) }
    }

    /**
     * API Method: [Get payment methods](https://api-reference.kevin.eu/public/platform/v0.3#tag/General-Service/operation/getPaymentMethods)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getPaymentMethods(): List<String> =
        httpClient.get {
            url(path = Endpoint.Paths.General.getPaymentMethods())
        }.body<ResponseArray<String>>().data

    /**
     * Equivalent of suspending `getPaymentMethods()` for Java interoperability
     */
    @JvmName("getPaymentMethods")
    fun getPaymentMethodsAsFuture(): CompletableFuture<List<String>> {
        return suspendingToCompletableFuture { getPaymentMethods() }
    }

    /**
     * API Method: [Get project settings](https://api-reference.kevin.eu/public/platform/v0.3#tag/General-Service/operation/getProjectSettings)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getProjectSettings(): GetProjectSettingsResponse =
        httpClient.get {
            url(path = Endpoint.Paths.General.getProjectSettings())
        }.body()

    /**
     * Equivalent of suspending `getProjectSettings()` for Java interoperability
     */
    @JvmName("getProjectSettings")
    fun getProjectSettingsAsFuture(): CompletableFuture<GetProjectSettingsResponse> {
        return suspendingToCompletableFuture { getProjectSettings() }
    }
}
