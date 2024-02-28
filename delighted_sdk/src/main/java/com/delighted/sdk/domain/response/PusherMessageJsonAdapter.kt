package com.delighted.sdk.domain.response

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi.Builder
import com.squareup.moshi.Types

class PusherMessageJsonAdapter : JsonAdapter<PusherMessageResponse>() {
    private val moshi = Builder().build()

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter(String::class.java)

    private val mapOfStringStringAdapter: JsonAdapter<Map<String, String>> =
        moshi.adapter(
            Types.newParameterizedType(
                Map::class.java, String::class.java,
                String::class.java
            ),
            emptySet(), "data"
        )

    @FromJson
    override fun fromJson(reader: JsonReader): PusherMessageResponse {
        var event: String? = null
        var data: Map<String, String>? = null
        var channel: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "event" -> event = reader.readJsonValue() as? String
                "data" -> {
                    @Suppress("UNCHECKED_CAST")
                    data = when (val value = reader.readJsonValue()) {
                        is String -> mapOfStringStringAdapter.fromJson(value.replace("\\", ""))
                        else -> value as? Map<String, String> ?: emptyMap()
                    }
                }
                "channel" -> channel = reader.readJsonValue() as? String
                else -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        return PusherMessageResponse(
            event = event.orEmpty(),
            `data` = data ?: emptyMap(),
            channel = channel,
        )
    }

    override fun toJson(writer: JsonWriter, value: PusherMessageResponse?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("event")
        stringAdapter.toJson(writer, value.event)
        writer.name("data")
        mapOfStringStringAdapter.toJson(writer, value.`data`)
        writer.endObject()
    }
}
