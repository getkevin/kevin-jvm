![kevin.](./images/logo.png)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/eu.kevin/kevin-jvm/badge.svg)](https://maven-badges.herokuapp.com/maven-central/eu.kevin/kevin-jvm)
[![License](https://shields.io/badge/license-MIT-blue)](https://github.com/getkevin/kevin-jvm/blob/master/LICENSE)

# kevin. JVM Client

JVM client implementing [kevin. platform API v0.3](https://api-reference.kevin.eu/public/platform/v0.3).

## Prerequisites

- Java 8+
- Ktor 2.x

## Installation

Package and installation instructions are available at the [Maven Central Repository](https://maven-badges.herokuapp.com/maven-central/eu.kevin/kevin-jvm)

### Maven
```
<dependency>
  <groupId>eu.kevin</groupId>
  <artifactId>kevin-jvm</artifactId>
  <version>1.0.3</version>
</dependency>
```

### Gradle
```
implementation 'eu.kevin:kevin-jvm:1.0.3'
```

## Usage Examples

Parameter names and response data match those defined in API documentation.

Detailed API documentation can be found <a href="https://docs.kevin.eu/public/platform" target="_blank">here</a>.

### Create a client object

```kotlin
import eu.kevin.api.services.Client

val client = Client(
    authorization = Authorization(
        clientId = "[YOUR_CLIENT_ID]",
        clientSecret = "[YOUR_CLIENT_SECRET]"
    )
)
```

### Call an API method
```kotlin
val request = InitiatePaymentRequest(
    redirectUrl = "https://yourapp.com/callback",
    amount = BigDecimal("12.34"),
    currencyCode = "EUR",
    description = "My payment",
    identifier = UserIdentifier(email = "jsmith@example.com"),
    bankPaymentMethod = BankPaymentMethod(
        endToEndId = "123",
        creditorName = "John Doe",
        informationStructured = InformationStructured(
            reference = "00220055",
            referenceType = "SCOR"
        ),
        requestedExecutionDate = LocalDate.of(2021, 3, 8),
        creditorAccount = Account(
            iban = "LT144010051005081586"
        )
    )
)

val response = try {
    client.paymentClient.initiatePayment(request)
} catch (e: KevinApiErrorException) {
    logger.error(e.responseBody?.error?.description, e)
    null
}
```

### Parse the request body of a webhook response
```kotlin
import eu.kevin.api.services.Parser

private val parser = Parser()

val webhookRequestBody = """
        {
            "id": "e4dd60bb-574f-4a13-910a-57c9795d905f",
            "bankStatus": "ACSC",
            "statusGroup": "completed",
            "type": "PAYMENT"
        }
    """.trimIndent()

val paymentWebhookPayload = parser.parsePaymentWebhookRequest(request = webhookRequestBody)
```

## Support

Email: help@kevin.eu

## License

- **[MIT license](LICENSE.md)**
- Copyright© 2022 <a href="https://www.kevin.eu/" target="_blank">kevin.</a>
