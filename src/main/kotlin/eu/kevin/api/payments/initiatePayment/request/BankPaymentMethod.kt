@file:UseSerializers(LocalDateSerializer::class)

package eu.kevin.api.payments.initiatePayment.request

import eu.kevin.api.serialization.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
data class BankPaymentMethod @JvmOverloads constructor(
    val endToEndId: String,
    val creditorName: String,
    val creditorAccount: Account,
    var debtorAccount: Account? = null,
    var requestedExecutionDate: LocalDate? = null,
    var informationStructured: InformationStructured? = null,
)
