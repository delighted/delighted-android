package plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByName
import utils.configureKotlin
import utils.libs
import java.io.File

class AndroidConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project.plugins) {
            apply("kotlin-android")
            apply("kotlin-parcelize")
            apply("kotlin-kapt")

            withType(AppPlugin::class.java) {
                project.extensions
                    .getByName<ApplicationExtension>("android")
                    .configure(project)
            }
            withType(LibraryPlugin::class.java) {
                project.extensions
                    .getByName<LibraryExtension>("android")
                    .configure(project)
            }

            apply("org.jetbrains.dokka")
        }
        project.configureKotlin()

        project.configurations.all {
            resolutionStrategy.eachDependency {
                if (requested.name == "javapoet") {
                    useVersion(project.libs.versions.javapoet.get())
                }
            }
        }
    }
}

fun ApplicationExtension.configure(project: Project) {
    configureCommon(project)

    with(defaultConfig) {
        targetSdk = Config.targetSdk
        versionCode = Config.code
        versionName = Config.name
    }

    lint {
        checkDependencies = true
        ignoreTestSources = true
        abortOnError = true
        xmlReport = true
        xmlOutput = File("${project.buildDir}/reports/lint/lint-result.xml")
        htmlReport = true
        htmlOutput = File("${project.buildDir}/reports/lint/lint-result.html")
    }
}

fun LibraryExtension.configure(project: Project) {
    configureCommon(project)

    // If a library module needs to define a set of ProGuard/R8 rules

    buildFeatures.buildConfig = false
}

fun CommonExtension<*, *, *, *>.configureCommon(project: Project) {
    compileSdk = Config.compileSdk

    with(defaultConfig) {
        minSdk = Config.minSdk
        buildToolsVersion = Config.buildTools
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
        }
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }

    testOptions.reportDir = "${project.rootDir}/reports/${project.displayName}"

    project.dependencies {
        "coreLibraryDesugaring"(project.libs.desugar.jdk)
    }
}
