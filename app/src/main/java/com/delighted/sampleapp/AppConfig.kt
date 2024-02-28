package com.delighted.sampleapp

import java.time.Instant

/**
 * Replacement for the auto-generated `BuildConfig` class. Contains all of
 * the usual values provided by the `BuildConfig` class, as well as some
 * additional values, such as the hash of the latest git commit and the
 * commit timestamp.
 */
interface AppConfig {
    val isDebug: Boolean
    val applicationId: String
    val buildType: String
    val versionCode: Int
    val versionName: String
    val sdkInt: Int
    val gitSha: GitHash
    val gitTimestamp: Instant
    val appCenterSecret: String
    val delightedId: String
}

@JvmInline
value class GitHash(val hash: String)

fun GitHash.abbreviated(): String = hash.substring(0, 7)
