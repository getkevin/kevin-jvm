package eu.kevin.api.services

import eu.kevin.api.Dependencies
import eu.kevin.api.models.payment.payment.webhook.PaymentWebhookPayload
import eu.kevin.api.models.payment.refund.webhook.PaymentRefundWebhookPayload
import kotlinx.serialization.json.Json

/**
 * Deserializes webhook request JSON payloads to JVM objects
 */
class Parser internal constructor(
    private val serializer: Json
) {
    constructor() : this(
        serializer = Dependencies.serializer
    )

    /**
     * Deserializes webhook payload of a bank payment
     *
     * @see eu.kevin.api.services.payment.PaymentClient.initiatePayment
     */
    fun parsePaymentWebhookRequest(request: String): PaymentWebhookPayload {
        return serializer.decodeFromString(PaymentWebhookPayload.serializer(), request)
    }

    /**
     * Deserializes webhook payload of a payment refund
     *
     * @see eu.kevin.api.services.payment.PaymentClient.initiatePaymentRefund
     */
    fun parsePaymentRefundWebhookRequest(request: String): PaymentRefundWebhookPayload {
        return serializer.decodeFromString(PaymentRefundWebhookPayload.serializer(), request)
    }
}
