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
    val id: String? = null,
    val isBooked: Boolean,
    val amount: BigDecimal,
    val currencyCode: String,
    val counterPartyName: String? = null,
    val counterPartyAccount: CounterPartyAccount,
    val informationStructured: InformationStructured? = null,
    val informationUnstructured: String? = null,
    val bookingDate: LocalDate? = null,
    val valueDate: LocalDate? = null
)
