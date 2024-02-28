package com.delighted.sampleapp

import android.os.Build
import com.delighted.BuildConfig
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DelightedSampleAppConfig @Inject constructor() : AppConfig {
    override val isDebug: Boolean = BuildConfig.DEBUG
    override val applicationId: String = BuildConfig.APPLICATION_ID
    override val buildType: String = BuildConfig.BUILD_TYPE
    override val versionCode: Int = BuildConfig.VERSION_CODE
    override val versionName: String = BuildConfig.VERSION_NAME
    override val sdkInt: Int = Build.VERSION.SDK_INT
    override val gitSha: GitHash = GitHash(BuildConfig.COMMIT_SHA)
    override val gitTimestamp: Instant =
        Instant.ofEpochMilli(BuildConfig.COMMIT_UNIX_TIMESTAMP.toLong())
    override val appCenterSecret: String = BuildConfig.APPCENTER_SECRET
    override val delightedId: String = BuildConfig.DELIGHTED_ID
}
