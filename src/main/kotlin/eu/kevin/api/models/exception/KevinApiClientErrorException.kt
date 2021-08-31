package eu.kevin.api.models.exception

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

class KevinApiClientErrorException internal constructor(
    val response: ErrorResponse
) : Exception() {
    @Serializable
    data class ErrorResponse(
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
