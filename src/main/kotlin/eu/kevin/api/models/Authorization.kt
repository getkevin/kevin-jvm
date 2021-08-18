package eu.kevin.api.models

sealed class Authorization {
    data class Default(val clientId: String, val clientSecret: String): Authorization()
    data class PointOfSale(val linkId: String): Authorization()
}
