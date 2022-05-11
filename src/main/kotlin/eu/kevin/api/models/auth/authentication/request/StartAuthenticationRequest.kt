package eu.kevin.api.models.auth.authentication.request

data class StartAuthenticationRequest @JvmOverloads constructor(
    val requestId: String,
    val redirectUrl: String,
    var webhookUrl: String? = null, // Needed just if AuthenticationScopes.PAYMENT_POS is present
    var bankId: String? = null,
    var redirectPreferred: Boolean? = null,
    var scopes: List<AuthenticationScopes>? = null,
    var email: String? = null,
    var cardMethod: CardMethod? = null,
    var lang: String? = null
)
