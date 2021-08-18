plugins {
    kotlin("jvm") version Versions.KOTLIN
    kotlin("plugin.serialization") version Versions.KOTLIN
    id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT
}

group = "eu.kevin.api"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-client-core:${Versions.KTOR}")
    implementation("io.ktor:ktor-client-cio:${Versions.KTOR}")
    implementation("io.ktor:ktor-client-serialization:${Versions.KTOR}")

    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-client-mock:${Versions.KTOR}")
}

tasks.test {
    useJUnitPlatform()
}

configureCodeStyleRules()

fun Project.configureCodeStyleRules() = configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    disabledRules.set(
        setOf(
            // can't be automatically fixed and does not affect code readability
            "no-wildcard-imports",
            // does not affect code readability
            "final-newline"
        )
    )
}