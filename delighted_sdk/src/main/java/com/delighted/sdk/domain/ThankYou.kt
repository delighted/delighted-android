package com.delighted.sdk.domain

data class ThankYou(
    val text: String,
    val autoCloseDelay: Int?,
    val thankYouMessageGroups: List<ThankYouMessageGroup>
)
