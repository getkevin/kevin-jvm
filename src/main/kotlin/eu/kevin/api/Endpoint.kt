package eu.kevin.api

internal object Endpoint {
    const val BASE = "https://api.kevin.eu/platform"
    const val VERSION = "/v0.3"

    object Path {
        object Payment {
            fun initiatePayment() = "/pis/payment"
            fun getPaymentStatus(paymentId: String) = "/pis/payment/$paymentId/status"
            fun initiatePaymentRefund(paymentId: String) = "/pis/payment/$paymentId/refunds"
        }
        object Auth {
            fun startAuthentication() = "/auth"
            fun receiveToken() = "/auth/token"
            fun receiveTokenContent() = "/auth/token/content"
        }
        object General {
            fun getSupportedCountries() = "/auth/countries"
            fun getSupportedBanks() = "/auth/banks"
            fun getSupportedBank(bankId: String) = "/auth/banks/$bankId"
            fun getSupportedBankByCardNumberPiece(cardNumberPiece: String) = "/auth/banks/cards/$cardNumberPiece"
            fun getPaymentMethods() = "/auth/paymentMethods"
            fun getProjectSettings() = "/auth/project/settings"
        }
    }
}
