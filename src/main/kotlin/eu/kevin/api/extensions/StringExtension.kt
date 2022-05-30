package eu.kevin.api.extensions

internal fun String.appendAtStartIfNotExist(
    stringToAppend: String
): String =
    if (this.startsWith(stringToAppend)) this else "$stringToAppend $this"
