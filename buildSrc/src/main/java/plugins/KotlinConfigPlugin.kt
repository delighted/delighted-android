package plugins

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.kotlin.dsl.getPlugin
import utils.configureKotlin

class KotlinConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            plugins.apply("kotlin")
            plugins.apply("kotlin-kapt")
            convention.getPlugin<JavaPluginConvention>().apply {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
                testReportDirName = "${project.rootDir}/reports/${project.displayName}"
            }
            plugins.apply("org.jetbrains.dokka")

            configureKotlin()
        }
    }
}
