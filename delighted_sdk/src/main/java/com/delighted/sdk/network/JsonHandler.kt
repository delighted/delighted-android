package com.delighted.sdk.network

import com.delighted.sdk.domain.request.PusherAuthRequest
import com.delighted.sdk.domain.request.SurveyAnswerRequest
import com.delighted.sdk.domain.request.SurveyRequest
import com.delighted.sdk.domain.response.PusherAuthResponse
import com.delighted.sdk.domain.response.PusherMessageJsonAdapter
import com.delighted.sdk.domain.response.PusherMessageResponse
import com.delighted.sdk.domain.response.SdkConfigurationResponse
import com.delighted.sdk.domain.response.SurveyRequestResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Inject

class JsonHandler @Inject constructor() {
    val moshi: Moshi = Moshi.Builder()
        .add(PusherMessageJsonAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    fun attemptDecodeSurveyConfig(rawJson: String): Result<SdkConfigurationResponse> {
        val adapter = moshi.adapter(SdkConfigurationResponse::class.java)

        return try {
            val decoded: SdkConfigurationResponse? = adapter?.fromJson(rawJson)
            requireNotNull(decoded) { "Json for SurveyConfiguration decoded to null with no exception" }
            Result.success(decoded)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    internal fun attemptDecodeSurveyRequestResponse(rawJson: String): Result<SurveyRequestResponse> {
        val adapter = moshi.adapter(SurveyRequestResponse::class.java)

        return try {
            val decoded: SurveyRequestResponse? =
                adapter?.fromJson(rawJson)
            requireNotNull(decoded) { "Json for SurveyRequestResponse decoded to null with no exception" }
            Result.success(decoded)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun attemptDecodePusherAuthResponse(rawJson: String): Result<PusherAuthResponse> {
        val adapter = moshi.adapter(PusherAuthResponse::class.java)

        return try {
            val decoded: PusherAuthResponse? =
                adapter?.fromJson(rawJson)
            requireNotNull(decoded) { "Json for PusherAuthResponse decoded to null with no exception" }
            Result.success(decoded)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun attemptDecodePusherMessage(message: String): Result<PusherMessageResponse> {
        val adapter = moshi.adapter(PusherMessageResponse::class.java)

        return try {
            val decoded: PusherMessageResponse? =
                adapter?.fromJson(message)
            requireNotNull(decoded) { "Json for PusherConnectMessageResponse decoded to null with no exception" }
            Result.success(decoded)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun encodeSurveyRequest(surveyRequest: SurveyRequest): Result<String> {
        val adapter = moshi.adapter(SurveyRequest::class.java)

        return when (val encoded: String? = adapter?.toJson(surveyRequest)) {
            null -> Result.failure(Throwable("Could not encode survey request $surveyRequest"))
            else -> Result.success(encoded)
        }
    }

    fun encodeSurveyAnswer(surveyAnswerRequest: SurveyAnswerRequest): Result<String> {
        val adapter = moshi.adapter(SurveyAnswerRequest::class.java)

        return when (val encoded: String? = adapter?.toJson(surveyAnswerRequest)) {
            null -> Result.failure(Throwable("Could not encode send-survey-response $surveyAnswerRequest"))
            else -> Result.success(encoded)
        }
    }

    fun encodePusherAuthRequest(pusherAuthRequest: PusherAuthRequest): Result<String> {
        val adapter = moshi.adapter(PusherAuthRequest::class.java)

        return when (val encoded: String? = adapter?.toJson(pusherAuthRequest)) {
            null -> Result.failure(Throwable("Could not encode pusher auth request $pusherAuthRequest"))
            else -> Result.success(encoded)
        }
    }
}
