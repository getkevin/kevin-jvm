package eu.kevin.api.models.account

data class AccountRequestHeaders(
    val accessToken: String,
    val psuIPAddress: String,
    val psuUserAgent: String,
    val psuIPPort: String,
    val psuHttpMethod: PsuHttpMethod,
    val psuDeviceId: String
)