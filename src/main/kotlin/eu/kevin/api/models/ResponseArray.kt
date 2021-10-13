package eu.kevin.api.models

import kotlinx.serialization.Serializable

@Serializable
internal data class ResponseArray<T>(
    val data: List<T>
)
