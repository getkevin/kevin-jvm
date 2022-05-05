package eu.kevin.api.services

import io.ktor.http.*

object UrlUtils {
    fun Url.appendQueryParameter(name: String, value: String?) =
        if (value == null) this
        else this.copy(
            parameters = parameters + parametersOf(name, value)
        )
}
