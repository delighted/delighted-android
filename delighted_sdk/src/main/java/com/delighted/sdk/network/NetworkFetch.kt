@file:Suppress("BlockingMethodInNonBlockingContext")

package com.delighted.sdk.network

import android.util.Base64
import android.util.Base64.NO_WRAP
import com.delighted.sdk.AppDispatchers
import com.delighted.sdk.BuildConfig
import com.delighted.sdk.network.NetworkFetch.RequestMethod.GET
import com.delighted.sdk.network.NetworkFetch.RequestMethod.POST
import com.delighted.sdk.repository.SurveyInitRepository
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import javax.inject.Inject

/**
 * Android-only networking layer, to avoid external dependencies
 */
class NetworkFetch @Inject constructor(
    private val surveyInitRepo: SurveyInitRepository,
    private val dispatchers: AppDispatchers
) {
    private val delightedId: String get() = surveyInitRepo.sdkInitParams.delightedId
    private val baseUrl: URL = URL("https://mobile-sdk-api.delighted.com/v1")
    private val baseCDNUrl: URL = URL("https://d2xjuvt32ceggq.cloudfront.net/v1")

    private fun urlWithUser(config: Boolean = false): URL {
        val base = when {
            !surveyInitRepo.sdkInitParams.serverUrl.isNullOrEmpty() -> surveyInitRepo.sdkInitParams.serverUrl
            config -> baseCDNUrl
            else -> baseUrl
        }
        return URL("$base/$delightedId")
    }

    suspend fun surveyAnswer(surveyResponseJson: String): Result<String> =
        withContext(dispatchers.io) {
            val surveyResponseUrl = URL("${urlWithUser()}/survey_responses")
            request(url = surveyResponseUrl, payload = surveyResponseJson, requestMethod = POST)
        }

    suspend fun requestSurvey(surveyRequestJson: String): Result<String> =
        withContext(dispatchers.io) {
            val surveyReqUrl = URL("${urlWithUser()}/survey_requests")
            request(url = surveyReqUrl, payload = surveyRequestJson, requestMethod = POST)
        }

    suspend fun requestPusherAuth(pusherAuthRequestJson: String): Result<String> =
        withContext(dispatchers.io) {
            val pusherAuthUrl = URL("${urlWithUser()}/pusher/auth")
            request(url = pusherAuthUrl, payload = pusherAuthRequestJson, requestMethod = POST)
        }

    suspend fun requestSdkConfiguration(): Result<String> =
        withContext(dispatchers.io) {
            val configUrl = URL("${urlWithUser(config = true)}/configuration")
            request(url = configUrl, requestMethod = GET)
        }

    private fun request(url: URL, payload: String = "", requestMethod: RequestMethod) =
        with(url.openConnection() as HttpURLConnection) {
            this.requestMethod = requestMethod.toString()
            setHeader()

            if (requestMethod == POST) {
                postPayload(payload).onFailure { return@with Result.failure(it) }
            }

            Timber.d("URL: $url")
            checkResponseCode()
                .onSuccess { Timber.d("Response code: $it") }
                .onFailure { return@with Result.failure(it) }

            val response = getResponse()
            Timber.d("Response: $response")

            response
        }

    private fun URLConnection.setHeader() {
        setRequestProperty(
            "Authorization",
            "basic " + Base64.encode(byteArrayOf(), NO_WRAP)
        )
        setRequestProperty("Accept", "application/json")
        setRequestProperty("Content-Type", "application/json")
        val buildVersion = BuildConfig.SDK_VERSION
        val sysInfo = System.getProperty("http.agent")
        setRequestProperty("User-Agent", "DelightedAndroidSDK/$buildVersion $sysInfo")
    }

    private fun HttpURLConnection.checkResponseCode(): Result<Int> {
        return try {
            Result.success(responseCode)
        } catch (e: java.io.IOException) {
            Result.failure(e)
        }
    }

    private fun URLConnection.getResponse(): Result<String> {
        val response = StringBuffer()
        val inputStreamReader = try {
            InputStreamReader(inputStream)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        BufferedReader(inputStreamReader).use {
            var inputLine = it.readLine()
            while (inputLine != null) {
                response.append(inputLine)
                inputLine = it.readLine()
            }
            it.close()
        }
        return Result.success(response.toString())
    }

    private fun URLConnection.postPayload(payload: String): Result<Unit> {
        return try {
            val wr = OutputStreamWriter(outputStream)
            wr.write(payload)
            wr.flush()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private enum class RequestMethod {
        GET,
        POST
    }
}
