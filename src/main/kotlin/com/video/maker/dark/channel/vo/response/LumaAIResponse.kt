package com.video.maker.dark.channel.vo.response

import com.fasterxml.jackson.annotation.JsonProperty

data class LumaAIResponse(
    val id: String,
    @JsonProperty("generation_type")
    val generationType: String,
    val state: String,
    @JsonProperty("failure_reason")
    val failureReason: String?,
    val createdAt: String? = null,
    val assets: List<Any>?,
    val model: String,
    val request: GenerationRequest
)

data class GenerationRequest(
    @JsonProperty("generation_type")
    val generationType: String,
    val prompt: String,
    @JsonProperty("aspect_ratio")
    val aspectRatio: String,
    val loop: Boolean,
    val keyframes: Map<String, Keyframe?>,
    @JsonProperty("callback_url")
    val callbackUrl: String?
)

data class Keyframe(
    val type: String,
    val url: String
)