package com.video.maker.dark.channel.vo.response

import com.fasterxml.jackson.annotation.JsonProperty

data class LumaAIGetImageRequest(
    val id: String,
    @JsonProperty("generation_type") val generationType: String,
    val state: String,
    @JsonProperty("failure_reason") val failureReason: Any?,
    @JsonProperty("created_at") val createdAt: String,
    val assets: Assets,
    val model: String,
    val request: Request
)

data class Assets(
    val video: String,
    val image: String
)

data class Request(
    @JsonProperty("generation_type") val generationType: String,
    val prompt: String,
    @JsonProperty("aspect_ratio") val aspectRatio: String,
    val loop: Boolean,
    val keyframes: Map<String, Keyframe?>,
    @JsonProperty("callback_url") val callbackUrl: Any?
)