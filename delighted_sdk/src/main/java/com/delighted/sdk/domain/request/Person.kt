package com.delighted.sdk.domain.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents a person with attributes
 * @param phoneNumber needs to be in E.164 format
 */
@JsonClass(generateAdapter = true)
data class Person(
    @Json(name = "name") val name: String? = null,
    @Json(name = "email") val email: String? = null,
    @Json(name = "phone_number") val phoneNumber: String? = null
)
