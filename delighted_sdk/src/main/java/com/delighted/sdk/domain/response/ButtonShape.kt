package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class ButtonShape {
    @Json(name = "square")
    SQUARE,

    @Json(name = "round_rect")
    ROUND_RECT,

    @Json(name = "circle")
    CIRCLE,
}
