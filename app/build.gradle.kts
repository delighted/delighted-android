import com.android.build.api.variant.BuildConfigField
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import plugins.Config
import utils.propOrEmpty
import java.net.URI

allprojects {
    repositories {
        maven {
            url = URI("https://s3.amazonaws.com/delighted-android-sdk-public")
        }
    }
}

plugins {
    id("com.android.application")
    id("android-config")
    id("hilt-config")
}

android {
    compileSdk = Config.compileSdk
    defaultConfig {
        applicationId = "com.delighted"
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.code
        versionName = Config.name
        testApplicationId = "com.delighted.test"
    }

    signingConfigs {
        findByName("debug")?.apply {
            storeFile = file("debug.keystore")
            storePassword = "debugAndroid"
            keyAlias = "debug"
            keyPassword = "debugAndroid"
        }

        if (file("release.keystore").exists()) {
            create("release") {
                storeFile = file("release.keystore")
                storePassword = propOrEmpty("STORE_PASSWORD")
                keyAlias = "template"
                keyPassword = propOrEmpty("KEY_PASSWORD")
            }
        }
    }

    flavorDimensions.add("track")

    productFlavors {
        create("develop") {
            dimension = "track"
            applicationIdSuffix = ".develop"
        }
        create("sprint") {
            dimension = "track"
            applicationIdSuffix = ".sprint"
        }
        create("production") {
            dimension = "track"
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            signingConfig = if (file("release.keystore").exists()) {
                signingConfigs.getByName("release")
            } else {
                signingConfigs.getByName("debug")
            }
        }
    }

    packagingOptions {
        excludes.add("META-INF/*.kotlin_module")
    }

    buildFeatures.viewBinding = true
}

fun appCenterSecret(): String =
    gradleLocalProperties(rootDir).getProperty("APPCENTER_SECRET")
        ?: propOrEmpty("APPCENTER_SECRET")

fun delightedId(): String =
    gradleLocalProperties(rootDir).getProperty("DELIGHTED_ID")
        ?: propOrEmpty("DELIGHTED_ID")

fun gitSha(): String {
    return cachedBuildFile(filename = "commit-sha.txt", "git", "rev-parse", "HEAD")
}

fun gitTimestamp(): String {
    return cachedBuildFile(
        filename = "commit-timestamp.txt",
        "git", "log", "-n", "1", "--format=%at"
    )
}

fun cachedBuildFile(filename: String, vararg command: String): String {
    val f = File(buildDir, filename)
    if (!f.exists()) {
        val p = ProcessBuilder(*command).directory(project.rootDir).start()
        if (p.waitFor() != 0) throw RuntimeException(p.errorStream.reader().readText())
        f.parentFile.mkdirs()
        f.outputStream().use { outputStream -> p.inputStream.copyTo(outputStream) }
    }
    return f.readText().trim()
}

androidComponents {
    onVariants {
        it.buildConfigFields.put(
            "COMMIT_SHA",
            BuildConfigField("String", "\"${gitSha()}\"", null)
        )
        it.buildConfigFields.put(
            "COMMIT_UNIX_TIMESTAMP",
            BuildConfigField("Long", "${gitTimestamp()}L", null)
        )
        it.buildConfigFields.put(
            "APPCENTER_SECRET",
            BuildConfigField("String", appCenterSecret(), null)
        )
        it.buildConfigFields.put(
            "DELIGHTED_ID",
            BuildConfigField("String", delightedId(), null)
        )
    }
}

dependencies {
    // To depend on the SDK directly from this project, use this instead of the maven coordinates
    // implementation(project(":delighted_sdk"))

    implementation("com.delighted.sdk", "android", "1.0.+")

    implementation(files("libs/lint-rules-release.aar"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.zacsweers.ticktock)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core)
    implementation(libs.material.components)
    implementation(libs.androidx.fragment)
    implementation(libs.appcenter.analytics)
    implementation(libs.appcenter.crashes)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.timber)

    debugImplementation(libs.leakcanary)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.ext.junit)

    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.androidx.test.ext.truth)
}
