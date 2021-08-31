package eu.kevin.api.client

import eu.kevin.api.Endpoint
import eu.kevin.api.payments.initiatePayment.request.InitiatePaymentRequest
import eu.kevin.api.payments.initiatePayment.response.InitiatePaymentResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class PaymentsClient internal constructor(
    private val httpClient: HttpClient
) {
    suspend fun initiatePayment(request: InitiatePaymentRequest): InitiatePaymentResponse =
        httpClient.post(
            path = Endpoint.Path.INITIATE_PAYMENT,
            body = request.paymentData
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
}