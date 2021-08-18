package eu.kevin.api.models

enum class EndpointVersion(val path: String) {
    V01("/v0.1"),
    V02("/v0.2"),
    V03("/v0.3")
}