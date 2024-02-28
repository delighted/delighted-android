package com.delighted.sdk

import com.delighted.sdk.domain.SdkConfiguration

sealed class SdkConfigResult {
    data class SdkConfigFail(val throwable: Throwable?) : SdkConfigResult()
    data class SdkConfigSuccess(val sdkConfiguration: SdkConfiguration) : SdkConfigResult()
}
