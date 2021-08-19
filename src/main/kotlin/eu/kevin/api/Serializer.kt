package eu.kevin.api

import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class Serializer(private val moshi: Moshi) {
    inline fun <reified T> serialize(obj: T): String = moshi.adapter(T::class.java).toJson(obj)

    suspend inline fun <reified T> deserialize(response: String): T = withContext(Dispatchers.IO) {
        moshi.adapter(T::class.java).nonNull().fromJson(response)!!
    }
}