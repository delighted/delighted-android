package com.delighted.sdk.interactor

import com.delighted.sdk.LocalCache
import com.delighted.sdk.SdkConfigResult
import com.delighted.sdk.SdkConfigResult.SdkConfigFail
import com.delighted.sdk.SdkConfigResult.SdkConfigSuccess
import com.delighted.sdk.domain.fromResponse
import com.delighted.sdk.network.JsonHandler
import com.delighted.sdk.network.NetworkFetch
import java.time.Instant
import javax.inject.Inject

/**
 * Sends a request for SDK configuration parameters from backend
 */
internal class GetSdkConfigurationUseCase @Inject constructor(
    private val network: NetworkFetch,
    private val jsonHandler: JsonHandler,
    private val localCache: LocalCache,
) : SuspendableUseCase<Unit, SdkConfigResult> {
    override suspend fun execute(params: Unit): SdkConfigResult {
        val sdkConfigResponse = try {
            network.requestSdkConfiguration().let { result ->
                result.onFailure { return SdkConfigFail(throwable = it) }
                jsonHandler.attemptDecodeSurveyConfig(rawJson = result.getOrThrow())
            }
        } catch (e: Exception) {
            return SdkConfigFail(throwable = e)
        }

        val sdkConfiguration = sdkConfigResponse.let { result ->
            result.onFailure { return SdkConfigFail(throwable = it) }
            fromResponse(sdkConfigResponse = result.getOrThrow())
        }

        // if config response is successful then that is an invocation of the API,
        // so we record it
        markFirstSeenTimestamp()

        return SdkConfigSuccess(sdkConfiguration = sdkConfiguration)
    }

    /**
     * If not already set, set now as as the "first time seen"
     */
    private fun markFirstSeenTimestamp() {
        localCache.apply {
            if (!isSeen) {
                firstSeenTimestamp = Instant.now().toEpochMilli()
            }
        }
    }
}
