package eu.kevin.api.models.account

internal interface AccountRequest {
    val accessToken: String
    val psuIPAddress: String
    val psuUserAgent: String
    val psuIPPort: String
    val psuHttpMethod: PsuHttpMethod
    val psuDeviceId: String
}