package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import utils.libs

/**
 * This is a custom plugin to easily configure Dagger in the module it is applied to. The module
 * just needs to add `apply plugin: 'dagger-config'` (or `plugins.apply(DaggerConfigPlugin::class)`
 * for the Kotlin DSL), no other configuration is required. This helps avoid mis-configured Dagger
 * dependency declarations inside of Gradle which can lead to build time slowdowns.
 */
class DaggerConfigPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("kotlin-kapt")

            dependencies {
                "implementation"(libs.dagger.runtime)
                "kapt"(libs.dagger.compiler)
            }
        }
    }
}
