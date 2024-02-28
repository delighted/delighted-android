import com.android.build.api.variant.BuildConfigField
import plugins.Config
import java.net.URI
import com.amazonaws.auth.profile.ProfileCredentialsProvider

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("android-config")
    id("hilt-config")
    id("maven-publish")
}

android.buildFeatures.viewBinding = true

android {
    buildFeatures {
        buildConfig = true
    }
}

androidComponents {
    onVariants {
        it.buildConfigFields.put(
            "SDK_VERSION",
            BuildConfigField("String", "\"${Config.name}\"", null)
        )
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.material.components)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    testImplementation(kotlin("test"))
    testImplementation(libs.mockk)

    // Moshi
    implementation(libs.moshi.kotlin)
    kapt(libs.moshi.codegen)

    // Webscoket
    implementation(libs.websocket)

    // Timber
    implementation(libs.timber)
}

tasks {
    val sourceJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(android.sourceSets["main"].java.srcDirs)
    }
}

android {
    publishing {
        multipleVariants {
            withSourcesJar()
            withJavadocJar()
            allVariants()
        }
    }
}

publishing {
    repositories {
        maven {
            url = URI("s3://delighted-android-sdk-public.s3.amazonaws.com")
            val awsCredentials = ProfileCredentialsProvider("s3maven").credentials
            credentials(AwsCredentials::class.java) {
                accessKey = awsCredentials.awsAccessKeyId
                secretKey = awsCredentials.awsSecretKey
            }
        }
    }
}

android.libraryVariants.all {
    val variant = this // this is a Closure not a lambda so you can't use 'it'
    if (variant.name == "release") {
        variant.outputs.all {
            val output = this
            publishing.publications.create(variant.name, MavenPublication::class.java) {
                // Maven coordinates
                groupId = "com.delighted.sdk"
                artifactId = "android"
                version = Config.name

                artifact(tasks["sourceJar"])
                artifact(output.outputFile)

                pom.withXml {
                    val dependencies = asNode().appendNode("dependencies")

                    // Filter out anything that's not an external dependency.
                    configurations.getByName(variant.name + "CompileClasspath").allDependencies
                        .filterIsInstance<ExternalDependency>().forEach {
                            val dependency = dependencies.appendNode("dependency")
                            dependency.appendNode("groupId", it.group)
                            dependency.appendNode("artifactId", it.name)
                            dependency.appendNode("version", it.version)
                        }
                }
            }
        }
    }
}

tasks.withType<PublishToMavenRepository> {
    dependsOn("bundleReleaseAar")
}
