import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.KOTLIN
    kotlin("plugin.serialization") version Versions.KOTLIN
    id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT
    `maven-publish`
}

group = "eu.kevin"
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

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
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

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("kotlin") {
            from(components["kotlin"])
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
        }
    }
}
