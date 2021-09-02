package eu.kevin.api.parser

import eu.kevin.api.Dependencies
import eu.kevin.api.payments.webhook.WebhookPayload
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