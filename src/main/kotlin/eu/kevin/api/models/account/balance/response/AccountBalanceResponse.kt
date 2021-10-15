@file:UseSerializers(BigDecimalSerializer::class)

package eu.kevin.api.models.account.balance.response

import eu.kevin.api.serializers.BigDecimalSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal

@Serializable
data class AccountBalanceResponse(
    val type: AccountBalanceType,
    val amount: BigDecimal,
    val currencyCode: String
)
