package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class SurveyConfigurationResponse(
    @Json(name = "text_base_direction") val textBaseDirection: String,
    @Json(name = "powered_by_link_text") val poweredByLinkText: String,
    @Json(name = "powered_by_link_url") val poweredByLinkUrl: String,
    @Json(name = "next_text") val nextText: String,
    @Json(name = "prev_text") val prevText: String,
    @Json(name = "select_one_text") val selectOneText: String,
    @Json(name = "select_many_text") val selectManyText: String,
    @Json(name = "submit_text") val submitText: String,
    @Json(name = "done_text") val doneText: String,
    @Json(name = "not_likely_text") val notLikelyText: String,
    @Json(name = "very_likely_text") val veryLikelyText: String,
    @Json(name = "pusher") val pusher: PusherConfigResponse?,
    @Json(name = "theme") val theme: SurveyThemeResponse
)
