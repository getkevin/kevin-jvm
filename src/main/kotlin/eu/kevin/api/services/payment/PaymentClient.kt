package eu.kevin.api.services.payment

import eu.kevin.api.Endpoint
import eu.kevin.api.exceptions.KevinApiErrorException
import eu.kevin.api.extensions.appendAtStartIfNotExist
import eu.kevin.api.extensions.suspendingToCompletableFuture
import eu.kevin.api.models.payment.payment.request.InitiatePaymentRequest
import eu.kevin.api.models.payment.payment.request.InitiatePaymentRequestBody
import eu.kevin.api.models.payment.payment.response.InitiatePaymentResponse
import eu.kevin.api.models.payment.paymentStatus.GetPaymentStatusRequest
import eu.kevin.api.models.payment.paymentStatus.GetPaymentStatusResponse
import eu.kevin.api.models.payment.refund.InitiatePaymentRefundRequest
import eu.kevin.api.models.payment.refund.InitiatePaymentRefundRequestBody
import eu.kevin.api.models.payment.refund.InitiatePaymentRefundResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.util.concurrent.CompletableFuture

/**
 * Implements API Methods of the [Payment initiation service](https://api-reference.kevin.eu/public/platform/v0.3#tag/Payment-Initiation-Service)
 */
class PaymentClient internal constructor(
    private val httpClient: HttpClient
) {
    /**
     * API Method: [Initiate payment](https://api-reference.kevin.eu/public/platform/v0.3#tag/Payment-Initiation-Service/operation/initiatePayment)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun initiatePayment(request: InitiatePaymentRequest): InitiatePaymentResponse =
        httpClient.post {
            url(path = Endpoint.Paths.Payment.initiatePayment())
            setBody(
                InitiatePaymentRequestBody(
                    amount = request.amount,
                    currencyCode = request.currencyCode,
                    description = request.description,
                    bankPaymentMethod = request.bankPaymentMethod,
                    cardPaymentMethod = request.cardPaymentMethod,
                    identifier = request.identifier
                )
            )
            request.run {
                parameter("bankId", bankId)
                parameter("redirectPreferred", redirectPreferred)
                parameter("paymentMethodPreferred", paymentMethodPreferred?.title)
                parameter("lang", request.lang)

                headers {
                    accessToken?.let {
                        append(HttpHeaders.Authorization, it.appendAtStartIfNotExist("Bearer "))
                    }
                    append("Redirect-URL", redirectUrl)
                    webhookUrl?.let { append("Webhook-URL", it) }
                }
            }
        }.body()

    /**
     * Equivalent of suspending `initiatePayment(request: InitiatePaymentRequest)` for Java interoperability
     */
    @JvmName("initiatePayment")
    fun initiatePaymentAsFuture(request: InitiatePaymentRequest): CompletableFuture<InitiatePaymentResponse> {
        return suspendingToCompletableFuture { initiatePayment(request) }
    }

    /**
     * API Method: [Get payment status](https://api-reference.kevin.eu/public/platform/v0.3#tag/Payment-Initiation-Service/operation/getPaymentStatus)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getPaymentStatus(request: GetPaymentStatusRequest): GetPaymentStatusResponse =
        httpClient.get {
            url(path = Endpoint.Paths.Payment.getPaymentStatus(paymentId = request.paymentId))
        }.body()

    /**
     * Equivalent of suspending `getPaymentStatus(request: GetPaymentStatusRequest)` for Java interoperability
     */
    @JvmName("getPaymentStatus")
    fun getPaymentStatusAsFuture(request: GetPaymentStatusRequest): CompletableFuture<GetPaymentStatusResponse> {
        return suspendingToCompletableFuture { getPaymentStatus(request) }
    }

    /**
     * API Method: [Initiate payment refund](https://api-reference.kevin.eu/public/platform/v0.3#tag/Payment-Initiation-Service/operation/initiatePaymentRefund)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun initiatePaymentRefund(request: InitiatePaymentRefundRequest): InitiatePaymentRefundResponse =
        httpClient.post {
            url(path = Endpoint.Paths.Payment.initiatePaymentRefund(paymentId = request.paymentId))
            setBody(InitiatePaymentRefundRequestBody(amount = request.amount))
            headers {
                request.webhookUrl?.let { append("Webhook-URL", it) }
            }
        }.body()

    /**
     * Equivalent of suspending ` initiatePaymentRefund(request: InitiatePaymentRefundRequest)` for Java interoperability
     */
    @JvmName("initiatePaymentRefund")
    fun initiatePaymentRefundAsFuture(request: InitiatePaymentRefundRequest): CompletableFuture<InitiatePaymentRefundResponse> {
        return suspendingToCompletableFuture { initiatePaymentRefund(request) }
    }
}
