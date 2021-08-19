package eu.kevin.api.models.exception

internal class KevinApiClientErrorException(
    val response: ErrorResponse
) : Exception()

internal data class ErrorResponse(
    val error: Error,
    val data: Any
)

internal data class Error(
    val code: Int,
    val name: String,
    val description: String
)