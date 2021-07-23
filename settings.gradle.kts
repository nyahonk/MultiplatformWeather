pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "MultiplatformWeather"
include(":androidApp")
include(":shared")