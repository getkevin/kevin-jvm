package eu.kevin.api.extensions

import io.ktor.http.*

internal fun Url.appendQueryParameter(name: String, value: String?) =
    if (value == null) this
    else this.copy(
        parameters = parameters + parametersOf(name, value)
    )
