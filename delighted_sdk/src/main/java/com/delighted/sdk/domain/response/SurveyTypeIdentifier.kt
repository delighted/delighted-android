package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class SurveyTypeIdentifier {
    @Json(name = "nps")
    NPS,

    @Json(name = "stars_five")
    STARS_FIVE,

    @Json(name = "csat")
    CSAT,

    @Json(name = "csat3")
    CSAT3,

    @Json(name = "ces7")
    CES7,

    @Json(name = "ces")
    CES,

    @Json(name = "enps")
    ENPS,

    @Json(name = "smileys")
    SMILEYS,

    @Json(name = "thumbs")
    THUMBS,

    @Json(name = "pmf")
    PMF,
}
