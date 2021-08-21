package eu.kevin.api

internal object Endpoint {
    const val BASE = "https://api.getkevin.eu/platform"
    const val VERSION = "/v0.3"

    object Path {
        const val INITIATE_PAYMENT = "/pis/payment"
    }
}
