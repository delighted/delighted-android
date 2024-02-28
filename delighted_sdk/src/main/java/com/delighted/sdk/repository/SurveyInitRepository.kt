package com.delighted.sdk.repository

import com.delighted.sdk.SdkInitParams
import com.delighted.sdk.domain.SdkConfiguration

/**
 * Data repository for the start parameters for a survey. Data stored here pertains to a survey
 * that may or may not be currently launched.
 */
interface SurveyInitRepository {
    var sdkInitParams: SdkInitParams
    var sdkConfiguration: SdkConfiguration
    fun reset()
}
