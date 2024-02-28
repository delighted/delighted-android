package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import utils.libs

/**
 * This is a custom plugin to easily configure Hilt in the module it is applied to. The module just
 * needs to add `apply plugin: 'hilt-config'` (or `plugins.apply(HiltConfigPlugin::class)` for the
 * Kotlin DSL), no other configuration is required. This helps avoid mis-configured Dagger
 * dependency declarations inside of Gradle which can lead to build time slowdowns.
 */
class HiltConfigPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("dagger.hilt.android.plugin")
            plugins.apply(DaggerConfigPlugin::class)

            dependencies {
                "implementation"(libs.dagger.hilt.android)
                "kapt"(libs.dagger.hilt.compiler)
            }
        }
    }
}
