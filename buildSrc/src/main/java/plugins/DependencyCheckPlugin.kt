package plugins

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.buffer
import okio.source
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

class DependencyCheckPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("uploadDependencyReport", UploadTask::class.java) {
            group = "help"
            inputFile.set(target.file("dependencyUpdates/report.json"))
            token.set(target.findProperty("token").toString())
        }

        target.afterEvaluate {
            tasks.findByName("generateDependencyReport")?.dependsOn("dependencyUpdates")
        }
    }
}

abstract class UploadTask : DefaultTask() {
    companion object {
        private val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
    }

    private val client by lazy {
        val logger = HttpLoggingInterceptor.Logger { message ->
            println(message)
        }
        val logging = HttpLoggingInterceptor(logger).apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
        OkHttpClient.Builder().addInterceptor(logging).build()
    }

    private val moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @get:OutputFile
    abstract val inputFile: RegularFileProperty

    @get:Input
    abstract val token: Property<String>

    @TaskAction
    fun upload() {
        val report = parseReport()
        val builder = StringBuilder()
        builder.appendReport(report)
        if (builder.isNotEmpty()) {
            val request = newRequest(builder.toString())
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    val message = """
                        Failed to create a Github issue ->
                        Status code: ${response.code}
                        Message: ${response.message}
                    """.trimIndent()
                    println(message)
                }
            }
        }
    }

    private fun parseReport(): Report? {
        val source = inputFile.get().asFile.source().buffer()
        return moshi.adapter(Report::class.java).fromJson(source)
    }

    private fun StringBuilder.appendReport(report: Report?): StringBuilder = apply {
        // First check for out of date dependencies and append them to the message
        val outdatedDependencies = report?.outdated
        if (outdatedDependencies != null && outdatedDependencies.count > 0) {
            appendOutdatedDependencies(outdatedDependencies)
        }

        // Then check if the Gradle version is out of date and append it to the message
        val gradle = report?.gradle
        val runningGradleVersion = gradle?.running?.version
        val currentGradleVersion = gradle?.current?.version
        if (runningGradleVersion != null &&
            currentGradleVersion != null &&
            runningGradleVersion < currentGradleVersion
        ) {
            appendGradleVersioning(runningGradleVersion, currentGradleVersion)
        }
    }

    private fun StringBuilder.appendOutdatedDependencies(
        outdatedDependencies: DependencyList
    ): StringBuilder = apply {
        append("### The following dependencies require updates").appendNewline()
        for (dependency in outdatedDependencies.dependencies) {
            appendCheckbox()
                .appendCode {
                    append("${dependency.group}:")
                    append("${dependency.name}:")
                    append("[${dependency.version} -> ${dependency.available?.release}]")
                }
                .appendNewline()
        }
    }

    private fun StringBuilder.appendGradleVersioning(
        runningGradleVersion: String,
        currentGradleVersion: String
    ): StringBuilder = apply {
        append("### Gradle is out of date")
            .appendNewline()
            .appendCheckbox()
            .appendCode {
                append("Gradle: [$runningGradleVersion -> $currentGradleVersion]")
            }
            .appendNewline()
    }

    private fun StringBuilder.appendCheckbox() = apply { append("- [ ] ") }

    private fun StringBuilder.appendNewline() = apply { append("\n") }

    private fun StringBuilder.appendCode(block: StringBuilder.() -> Unit) = apply {
        append("`")
        block()
        append("`")
    }

    private fun newRequest(body: String): Request {
        val issue = Issue(title = "Outdated Dependencies", body = body)
        val json = moshi.adapter(Issue::class.java).toJson(issue)
        val requestBody = json.toRequestBody(JSON)
        return Request.Builder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "token ${token.get()}")
            .url("https://api.github.com/repos/Rightpoint/android-template-v2/issues")
            .post(requestBody)
            .build()
    }
}

data class Issue(val title: String, val body: String)

data class Report(
    val gradle: GradleVersionReport,
    val current: DependencyList,
    val exceeded: DependencyList,
    val outdated: DependencyList,
    val unresolved: DependencyList,
    val count: Int
)

data class DependencyList(val dependencies: List<Dependency>, val count: Int)

data class Dependency(
    val name: String,
    val group: String,
    val version: String,
    val available: Available?,
    val latest: String?,
    val reason: String?,
    val projectUrl: String?
)

data class Available(
    val release: String?,
    val milestone: String?,
    val integration: String?
)

data class GradleVersionReport(
    val current: GradleVersion,
    val nightly: GradleVersion,
    val releaseCandidate: GradleVersion,
    val running: GradleVersion,
    val enabled: Boolean
)

data class GradleVersion(
    val version: String,
    val reason: String,
    val isUpdateAvailable: Boolean,
    val isFailure: Boolean
)
