package eu.kevin.api.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = LocalDate::class)
object LocalDateSerializer : KSerializer<LocalDate> {
    private val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun serialize(encoder: Encoder, value: LocalDate) {
        encoder.encodeString(value.format(format))
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        val date = decoder.decodeString()
        return try {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE_TIME)
        } catch (exception: DateTimeParseException) {
            LocalDate.parse(date, format)
        }
    }
}
