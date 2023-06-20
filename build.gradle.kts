import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    kotlin("jvm") version Versions.KOTLIN
    kotlin("plugin.serialization") version Versions.KOTLIN
    id("org.jetbrains.dokka") version Versions.KOTLIN
    id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version Versions.NEXUS_PUBLISH
}

group = "eu.kevin"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-client-core:${Versions.KTOR}")
    implementation("io.ktor:ktor-client-cio:${Versions.KTOR}")
    implementation("io.ktor:ktor-client-serialization:${Versions.KTOR}")
    implementation("io.ktor:ktor-client-content-negotiation:${Versions.KTOR}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${Versions.KTOR}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")

    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-client-mock:${Versions.KTOR}")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
        }
    }

    create<Jar>("dokkaJar") {
        archiveClassifier.set("javadoc")
        from(dokkaJavadoc)
        dependsOn(dokkaJavadoc)
    }

    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }
}

tasks.test {
    useJUnitPlatform()
}

configureCodeStyleRules()

fun Project.configureCodeStyleRules() = configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    disabledRules.set(
        setOf(
            // can't be automatically fixed and does not affect code readability
            "no-wildcard-imports"
        )
    )
}

val props = File("./local.properties").let { file ->
    Properties().apply {
        if (file.exists()) {
            load(file.inputStream())
        } else {
            setProperty("ossrhUsername", System.getenv("OSSRH_USERNAME"))
            setProperty("ossrhPassword", System.getenv("OSSRH_PASSWORD"))
            setProperty("mavenSigningKeyId", System.getenv("MAVEN_SIGNING_KEY_ID"))
            setProperty("mavenSigningKey", System.getenv("MAVEN_SIGNING_KEY"))
            setProperty("mavenSigningKeyPassword", System.getenv("MAVEN_SIGNING_KEY_PASSWORD"))
            setProperty("sdkVersion", System.getenv("SDK_RELEASE_VERSION"))
        }
    }
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("kotlin") {
            from(components["kotlin"])

            artifact(tasks["sourcesJar"])
            artifact(tasks["dokkaJar"])
            version = props.getProperty("sdkVersion")

            pom {
                name.set("kevin. JVM")
                description.set("JVM client implementing kevin. platform API")
                url.set("https://github.com/getkevin/kevin-jvm")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                    }
                }

                developers {
                    developer {
                        id.set("mobile-kevin-dev")
                        name.set("Kevin mobile developers")
                        email.set("mobile@kevin.eu")
                    }
                }

                scm {
                    connection.set("scm:git:github.com/getkevin/kevin-jvm.git")
                    developerConnection.set("scm:git:ssh://github.com/getkevin/kevin-jvm.git")
                    url.set("https://github.com/getkevin/kevin-jvm/tree/master")
                }
            }
        }
    }
}

signing {
    isRequired = true
    useInMemoryPgpKeys(
        props.getProperty("mavenSigningKeyId"),
        props.getProperty("mavenSigningKey"),
        props.getProperty("mavenSigningKeyPassword")
    )
    sign(tasks["sourcesJar"], tasks["dokkaJar"])
    sign(publishing.publications["kotlin"])
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(props.getProperty("ossrhUsername"))
            password.set(props.getProperty("ossrhPassword"))
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
