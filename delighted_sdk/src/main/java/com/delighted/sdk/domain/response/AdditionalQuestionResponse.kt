package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdditionalQuestionResponse(
    @Json(name = "id") val id: String,
    @Json(name = "type") val type: AdditionalQuestionType,
    @Json(name = "text") val text: String,
    @Json(name = "scale_min") val scaleMin: Int? = null,
    @Json(name = "scale_min_label") val scaleMinLabel: String? = null,
    @Json(name = "scale_max") val scaleMax: Int? = null,
    @Json(name = "scale_max_label") val scaleMaxLabel: String? = null,
    @Json(name = "options") val options: List<AdditionQuestionOptionResponse> = emptyList(),
    @Json(name = "target_audience_groups") val targetAudienceGroups: List<String> = emptyList()
) {

    @JsonClass(generateAdapter = false)
    enum class AdditionalQuestionType {
        @Json(name = "free_response")
        FREE_RESPONSE,

        @Json(name = "scale")
        SCALE,

        @Json(name = "select_one")
        SELECT_ONE,

        @Json(name = "select_many")
        SELECT_MANY
    }
}
