import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    alias(libs.plugins.benmanes.versioning)
}

configure<GradlePluginDevelopmentExtension> {
    plugins.apply {
        create("android-config") {
            id = "android-config"
            implementationClass = "plugins.AndroidConfigPlugin"
        }
        create("kotlin-config") {
            id = "kotlin-config"
            implementationClass = "plugins.KotlinConfigPlugin"
        }
        create("distribution-config") {
            id = "distribution-config"
            implementationClass = "plugins.DistributionPlugin"
        }
        create("dagger-config") {
            id = "dagger-config"
            implementationClass = "plugins.DaggerConfigPlugin"
        }
        create("hilt-config") {
            id = "hilt-config"
            implementationClass = "plugins.HiltConfigPlugin"
        }
        create("dependencies") {
            id = "dependencies"
            implementationClass = "plugins.DependencyCheckPlugin"
        }
    }
}

sourceSets {
    main.configure {
        java.srcDir(project.file("$buildDir/generated/sources/version-templates/kotlin/main"))
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = libs.versions.jvm.get()
    }
}

repositories {
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    google()
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.gradle.plugin.kotlin)
    implementation(libs.gradle.plugin.spotless)
    implementation(libs.dokka.core)
    implementation(libs.gradle.plugin.dokka)
    implementation(libs.jgit)
    implementation(libs.okhttp.runtime)
    implementation(libs.okhttp.interceptor.logging)
    implementation(libs.moshi.runtime)
    implementation(libs.moshi.kotlin)
    implementation(libs.javapoet) // <-- added this to resolve dagger version discrepancy
    // This is where  org.gradle.accessors.dm.LibrariesForLibs comes from
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
