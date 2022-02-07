import java.util.Properties

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val ktor_kafka_version: String by project
val kafka_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

group = "com.levels"
version = "0.0.1"

application {
    mainClass.set("com.levels.ApplicationKt")
}

val props = Properties()
props.load(file(".env").inputStream())
repositories {
    mavenCentral()
    // Need a GH access token with read package scope
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/karlazzampersonal/ktor-kafka")
        credentials {
            username = props.getProperty("USERNAME")
            password = props.getProperty("TOKEN")
        }
    }
}

ktlint {
    disabledRules.set(mutableListOf("no-wildcard-imports"))
    filter {
        exclude("**/generated/**")
        exclude { element -> element.file.path.contains("generated/") }
        include("**/kotlin/**")
    }
}

dependencies {
    // 2 dependencies added for kafka: kafka-clients and ktor-kafka
    api("org.apache.kafka:kafka-clients:$kafka_version")
    implementation("com.levels:ktor-kafka:$ktor_kafka_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
