package com.delighted.sdk.repository

import com.delighted.sdk.SdkInitParams
import com.delighted.sdk.domain.SdkConfiguration
import javax.inject.Inject

internal class SurveyInitRepositoryImpl @Inject constructor() : SurveyInitRepository {

    private var _sdkInitParams: SdkInitParams? = null
    private var _sdkConfiguration: SdkConfiguration? = null

    override var sdkInitParams: SdkInitParams
        get() = requireNotNull(_sdkInitParams) { "SDK initial params not set" }
        set(value) {
            _sdkInitParams = value
        }

    override var sdkConfiguration: SdkConfiguration
        get() = requireNotNull(_sdkConfiguration) { "SDK config not yet fetched" }
        set(value) {
            _sdkConfiguration = value
        }

    override fun reset() {
        _sdkInitParams = null
        _sdkConfiguration = null
    }
}
