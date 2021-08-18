package eu.kevin.api

enum class EndpointVersion(val path: String) {
    V01("/v0.1"),
    V02("/v0.2"),
    V03("/v0.3")
}

internal object Endpoint {
    const val BASE = "https://api.getkevin.eu/platform"

    object Path {
        const val INITIATE_PAYMENT = "/pis/payment"
    }
}
