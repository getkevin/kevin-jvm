package eu.kevin.api.services.payment

import eu.kevin.api.Endpoint
import eu.kevin.api.models.payment.getPaymentStatus.GetPaymentStatusRequest
import eu.kevin.api.models.payment.getPaymentStatus.GetPaymentStatusResponse
import eu.kevin.api.models.payment.initiatePayment.request.InitiatePaymentRequest
import eu.kevin.api.models.payment.initiatePayment.request.InitiatePaymentRequestBody
import eu.kevin.api.models.payment.initiatePayment.response.InitiatePaymentResponse
import eu.kevin.api.models.payment.initiatePaymentRefund.InitiatePaymentRefundRequest
import eu.kevin.api.models.payment.initiatePaymentRefund.InitiatePaymentRefundRequestBody
import eu.kevin.api.models.payment.initiatePaymentRefund.InitiatePaymentRefundResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class PaymentClient internal constructor(
    private val httpClient: HttpClient
) {
    suspend fun initiatePayment(request: InitiatePaymentRequest): InitiatePaymentResponse =
        httpClient.post(
            path = Endpoint.Path.initiatePayment(),
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
                    accessToken?.let { append(HttpHeaders.Authorization, "Bearer $it") }
                    append("Redirect-URL", redirectUrl)
                    webhookUrl?.let { append("Webhook-URL", it) }
                }
            }
        }

    suspend fun getPaymentStatus(request: GetPaymentStatusRequest): GetPaymentStatusResponse =
        httpClient.get(
            path = Endpoint.Path.getPaymentStatus(paymentId = request.paymentId)
        ) {
            request.run {
                headers {
                    accessToken?.let { append(HttpHeaders.Authorization, "Bearer $it") }
                }
            }
        }

    suspend fun initiatePaymentRefund(request: InitiatePaymentRefundRequest): InitiatePaymentRefundResponse =
        httpClient.post(
            path = Endpoint.Path.initiatePaymentRefund(paymentId = request.paymentId),
            body = InitiatePaymentRefundRequestBody(amount = request.amount)
        ) {
            request.run {
                headers {
                    webhookUrl?.let { append("Webhook-URL", it) }
                }
            }
        }
}