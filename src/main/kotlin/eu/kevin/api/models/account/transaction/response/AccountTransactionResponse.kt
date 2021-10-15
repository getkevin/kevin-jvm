@file:UseSerializers(BigDecimalSerializer::class, LocalDateSerializer::class)

package eu.kevin.api.models.account.transaction.response

import eu.kevin.api.serializers.BigDecimalSerializer
import eu.kevin.api.serializers.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.LocalDate

@Serializable
data class AccountTransactionResponse(
    val id: String,
    val isBooked: Boolean,
    val amount: BigDecimal,
    val currencyCode: String,
    val counterPartyName: String,
    val counterPartyAccount: CounterPartyAccount,
    val informationStructured: InformationStructured,
    val informationUnstructured: String,
    val bookingDate: LocalDate,
    val valueDate: LocalDate? = null
)
