package eu.kevin.api.services

import eu.kevin.api.Dependencies
import eu.kevin.api.models.payment.webhook.WebhookPayload
import kotlinx.serialization.json.Json

class Parser internal constructor(
    private val serializer: Json
) {
    constructor() : this(
        serializer = Dependencies.serializer
    )

    fun parseWebhookRequest(request: String): WebhookPayload {
        return serializer.decodeFromString(WebhookPayload.serializer(), request)
    }
}