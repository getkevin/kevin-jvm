package eu.kevin.api

import io.ktor.client.*

internal object Dependencies {
    val httpClient by lazy {
        HttpClient {  }
    }
}