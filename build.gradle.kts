plugins {
    kotlin("jvm") version Versions.Kotlin
    id("org.jlleitschuh.gradle.ktlint") version Versions.KtLint
}

group = "eu.kevin.api"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-client-core:${Versions.Ktor}")
    implementation("io.ktor:ktor-client-cio:${Versions.Ktor}")
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