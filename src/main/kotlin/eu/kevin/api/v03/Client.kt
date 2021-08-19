package eu.kevin.api.v03

import eu.kevin.api.Endpoint
import eu.kevin.api.Serializer
import eu.kevin.api.v03.payments.InitiatePaymentRequest
import eu.kevin.api.v03.payments.InitiatePaymentResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class Client internal constructor(
    private val httpClient: HttpClient,
    private val serializer: Serializer
) {
    suspend fun initiatePayment(request: InitiatePaymentRequest): InitiatePaymentResponse {
        val httpResponse: HttpResponse = httpClient.post(path = Endpoint.Path.INITIATE_PAYMENT) {
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
            body = serializer.serialize(request.paymentData)
        }
        return serializer.deserialize(httpResponse.readText())
    }
}