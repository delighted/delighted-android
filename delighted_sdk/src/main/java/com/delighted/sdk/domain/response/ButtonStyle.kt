package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class ButtonStyle {
    @Json(name = "outline")
    OUTLINE,

    @Json(name = "solid")
    SOLID,
}
