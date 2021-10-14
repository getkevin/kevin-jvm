package eu.kevin.api.services.payment

import eu.kevin.api.Endpoint
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

class PaymentClient internal constructor(
    private val httpClient: HttpClient
) {
    suspend fun initiatePayment(request: InitiatePaymentRequest): InitiatePaymentResponse =
        httpClient.post(
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
                    accessToken?.let { append(HttpHeaders.Authorization, "Bearer $it") }
                    append("Redirect-URL", redirectUrl)
                    webhookUrl?.let { append("Webhook-URL", it) }
                }
            }
        }

    suspend fun getPaymentStatus(request: GetPaymentStatusRequest): GetPaymentStatusResponse =
        httpClient.get(
            path = Endpoint.Paths.Payment.getPaymentStatus(paymentId = request.paymentId)
        ) {
            request.run {
                headers {
                    accessToken?.let { append(HttpHeaders.Authorization, "Bearer $it") }
                }
            }
        }

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