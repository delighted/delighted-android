package com.delighted.sdk

import android.content.SharedPreferences
import javax.inject.Inject

class LocalCache @Inject internal constructor(
    private val sharedPreferences: SharedPreferences
) {
    val isSeen: Boolean = sharedPreferences.getLong(FIRST_SEEN_TIMESTAMP, NOT_SET) != NOT_SET
    val isSurveyed: Boolean = sharedPreferences.getLong(LAST_SURVEYED_TIMESTAMP, NOT_SET) != NOT_SET

    var firstSeenTimestamp: Long
        get() = sharedPreferences.getLong(FIRST_SEEN_TIMESTAMP, NOT_SET)
        set(value) {
            sharedPreferences.edit().putLong(FIRST_SEEN_TIMESTAMP, value).apply()
        }

    var lastSurveyedTimestamp: Long
        get() = sharedPreferences.getLong(LAST_SURVEYED_TIMESTAMP, NOT_SET)
        set(value) {
            sharedPreferences.edit().putLong(LAST_SURVEYED_TIMESTAMP, value).apply()
        }

    companion object {
        const val LOCAL_CACHE_KEY = "delightedLocalCacheKey"
        private const val NOT_SET = -1L
        private const val FIRST_SEEN_TIMESTAMP = "first_seen_timestamp"
        private const val LAST_SURVEYED_TIMESTAMP = "last_surveyed_timestamp"
    }
}
