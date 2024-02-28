package utils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.propOrEmpty(name: String): String {
    return if (hasProperty(name)) findProperty(name)!!.toString() else ""
}

inline val Project.libs
    get() = extensions.getByName("libs") as org.gradle.accessors.dm.LibrariesForLibs

fun Project.configureKotlin() {
    extensions.getByType<KaptExtension>().apply {
        this.correctErrorTypes = true
        arguments {
            arg("dagger.formatGeneratedSource", "disabled")
        }
    }
    tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions.jvmTarget = libs.versions.jvm.get()
    }

    configurations.all {
        resolutionStrategy {
            eachDependency {
                if (requested.group == "org.jetbrains.kotlin") {
                    useVersion(libs.versions.kotlin.get())
                }
            }
        }
    }
}
