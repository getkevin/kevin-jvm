package eu.kevin.api.exceptions

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

class KevinApiErrorException internal constructor(
    val responseStatusCode: Int,
    val responseBody: ClientError?,
    val externalMessage: String?
) : Exception() {

    @Serializable
    data class ClientError(
        val error: Error,
        val data: JsonElement
    )

    @Serializable
    data class Error(
        val code: Int,
        val name: String,
        val description: String
    )
}
