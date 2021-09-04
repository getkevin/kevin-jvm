package eu.kevin.api.models.exception

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

class KevinApiErrorException internal constructor(
    val responseStatusCode: Int,
    val responseBody: ClientError?
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
