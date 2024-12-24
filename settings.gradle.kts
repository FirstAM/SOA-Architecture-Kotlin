rootProject.name = "arch-soa"

include(
    "order",
    "user",
    "network-core",
    "notification-service"
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        mavenLocal()
    }
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        mavenLocal()
    }
    versionCatalogs {
        create("libs") {
            from(files("libs.toml"))
        }
    }
}
