package eu.kevin.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*

internal object Dependencies {
    val httpClient by lazy {
        HttpClient(CIO) {
            install(JsonFeature)
        }
    }
}