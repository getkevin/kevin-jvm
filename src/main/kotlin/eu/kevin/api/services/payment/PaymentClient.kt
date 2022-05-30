package eu.kevin.api.services.payment

import eu.kevin.api.Endpoint
import eu.kevin.api.exceptions.KevinApiErrorException
import eu.kevin.api.extensions.appendAtStartIfNotExist
import eu.kevin.api.extensions.appendQueryParameter
import eu.kevin.api.models.payment.payment.request.InitiatePaymentRequest
import eu.kevin.api.models.payment.payment.request.InitiatePaymentRequestBody
import eu.kevin.api.models.payment.payment.response.InitiatePaymentResponse
import eu.kevin.api.models.payment.paymentStatus.GetPaymentStatusRequest
import eu.kevin.api.models.payment.paymentStatus.GetPaymentStatusResponse
import eu.kevin.api.models.payment.refund.InitiatePaymentRefundRequest
import eu.kevin.api.models.payment.refund.InitiatePaymentRefundRequestBody
import eu.kevin.api.models.payment.refund.InitiatePaymentRefundResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * Implements API Methods of the [Payment initiation service](https://docs.kevin.eu/public/platform/v0.3#tag/Payment-Initiation-Service)
 */
class PaymentClient internal constructor(
    private val httpClient: HttpClient
) {
    /**
     * API Method: [Initiate payment](https://docs.kevin.eu/public/platform/v0.3#operation/initiatePayment)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun initiatePayment(request: InitiatePaymentRequest): InitiatePaymentResponse =
        httpClient.post<InitiatePaymentResponse>(
            path = Endpoint.Paths.Payment.initiatePayment(),
            body = InitiatePaymentRequestBody(
                amount = request.amount,
                currencyCode = request.currencyCode,
                description = request.description,
                bankPaymentMethod = request.bankPaymentMethod,
                cardPaymentMethod = request.cardPaymentMethod,
                identifier = request.identifier
            )
        ) {
            request.run {
                parameter("bankId", bankId)
                parameter("redirectPreferred", redirectPreferred)
                parameter("paymentMethodPreferred", paymentMethodPreferred?.title)

                headers {
                    accessToken?.let {
                        append(HttpHeaders.Authorization, it.appendAtStartIfNotExist("Bearer"))
                    }
                    append("Redirect-URL", redirectUrl)
                    webhookUrl?.let { append("Webhook-URL", it) }
                }
            }
        }.run {
            copy(
                confirmLink = confirmLink?.let {
                    Url(it).appendQueryParameter("lang", request.lang).toString()
                }
            )
        }

    /**
     * API Method: [Get payment status](https://docs.kevin.eu/public/platform/v0.3#operation/getPaymentStatus)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun getPaymentStatus(request: GetPaymentStatusRequest): GetPaymentStatusResponse =
        httpClient.get(
            path = Endpoint.Paths.Payment.getPaymentStatus(paymentId = request.paymentId)
        )

    /**
     * API Method: [Initiate payment refund](https://docs.kevin.eu/public/platform/v0.3#operation/initiatePaymentRefund)
     */
    @Throws(KevinApiErrorException::class)
    suspend fun initiatePaymentRefund(request: InitiatePaymentRefundRequest): InitiatePaymentRefundResponse =
        httpClient.post(
            path = Endpoint.Paths.Payment.initiatePaymentRefund(paymentId = request.paymentId),
            body = InitiatePaymentRefundRequestBody(amount = request.amount)
        ) {
            request.run {
                headers {
                    webhookUrl?.let { append("Webhook-URL", it) }
                }
            }
        }
}
