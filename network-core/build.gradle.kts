plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

group = "test"
version = "1.0-SNAPSHOT"


kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }

        jvmToolchain(17)
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlin.ktor)
                api(libs.ktor.client.logging)
                api(libs.kotlin.ktor.negotiation)
                api(libs.kotlin.serialization.json)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.kotlin.ktor.okhttp)
                api("io.ktor:ktor-server-netty:2.2.4")
                api("io.ktor:ktor-server-html-builder-jvm:2.2.4")
                api("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.1")
                api("io.ktor:ktor-serialization-kotlinx-protobuf:2.2.4")
                api("io.ktor:ktor-server-content-negotiation:2.2.4")
                api("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.2")
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
                api("org.junit.jupiter:junit-jupiter-api:5.7.1")
                api("org.junit.jupiter:junit-jupiter-engine:5.7.1")
                api("org.mockito:mockito-core:3.11.2")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
            }
        }
        val jvmTest by getting
    }
}
