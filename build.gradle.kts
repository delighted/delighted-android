import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import java.lang.System.getProperty

plugins {
    id("org.jetbrains.dokka")
    alias(libs.plugins.android.cachefix) apply false
    id("dependencies")
}

apply {
    from(rootProject.file("gradle/spotless-config.gradle"))
    from(rootProject.file("gradle/projectDependencyGraph.gradle"))
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    apply(plugin = "com.github.ben-manes.versions")
}

subprojects {
    plugins.withType<com.android.build.gradle.AppPlugin> {
        apply(plugin = "org.gradle.android.cache-fix")
    }
    plugins.withType<com.android.build.gradle.LibraryPlugin> {
        apply(plugin = "org.gradle.android.cache-fix")
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }

    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.gradle.plugin.kotlin)
        classpath(libs.gradle.plugin.benmanes.versioning)
        classpath(libs.gradle.plugin.hilt)
        classpath(libs.gradle.plugin.dokka)
        classpath(libs.gradle.plugin.spotless)
        classpath("com.amazonaws", "aws-java-sdk-core", "1.11.5")
    }
}

fun String.isNonStable(): Boolean {
    val stableKeyword = listOf("RC", "RELEASE", "FINAL", "GA").any {
        this.toUpperCase().contains(it)
    }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return isStable.not()
}

tasks.named("dependencyUpdates", DependencyUpdatesTask::class.java).configure {
    rejectVersionIf {
        candidate.version.isNonStable()
    }
    revision = "release"
    outputFormatter = "json"
    outputDir = "dependencyUpdates"
    reportfileName = "report"
}

tasks.register<Copy>("installGitHooks") {
    from("$projectDir/git-hooks")
    into(File(projectDir, ".git/hooks"))
}

fun propOrEmpty(name: String): String {
    return if (hasProperty(name)) getProperty(name) else ""
}

tasks.dokkaGfmCollector.configure {
    outputDirectory.set(file("${rootProject.rootDir}/docs"))
}
