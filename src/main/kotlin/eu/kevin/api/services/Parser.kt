package eu.kevin.api.services

import eu.kevin.api.Dependencies
import eu.kevin.api.models.payment.payment.webhook.BankPaymentWebhookPayload
import eu.kevin.api.models.payment.payment.webhook.CardPaymentWebhookPayload
import eu.kevin.api.models.payment.payment.webhook.HybridPaymentWebhookPayload
import eu.kevin.api.models.payment.refund.webhook.PaymentRefundWebhookPayload
import kotlinx.serialization.json.Json

class Parser internal constructor(
    private val serializer: Json
) {
    constructor() : this(
        serializer = Dependencies.serializer
    )

    fun parseBankPaymentWebhookRequest(request: String): BankPaymentWebhookPayload {
        return serializer.decodeFromString(BankPaymentWebhookPayload.serializer(), request)
    }

    fun parseCardPaymentWebhookRequest(request: String): CardPaymentWebhookPayload {
        return serializer.decodeFromString(CardPaymentWebhookPayload.serializer(), request)
    }

    fun parseHybridPaymentWebhookRequest(request: String): HybridPaymentWebhookPayload {
        return serializer.decodeFromString(HybridPaymentWebhookPayload.serializer(), request)
    }

    fun parsePaymentRefundWebhookRequest(request: String): PaymentRefundWebhookPayload {
        return serializer.decodeFromString(PaymentRefundWebhookPayload.serializer(), request)
    }
}