package eu.kevin.api

enum class EndpointVersion(val path: String) {
    V01("/v0.1"),
    V02("/v0.2"),
    V03("/v0.3")
}

internal object Endpoint {
    const val Base = "https://api.getkevin.eu/platform"

    object Path {
        const val InitiatePayment = "/pis/payment"
    }
}
