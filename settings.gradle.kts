include(
    ":app",
    ":delighted_sdk",
)

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

enableFeaturePreview("VERSION_CATALOGS")
