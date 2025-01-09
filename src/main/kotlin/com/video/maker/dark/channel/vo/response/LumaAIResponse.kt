package com.video.maker.dark.channel.vo.response

data class LumaAIResponse(
    val id: String,
    val generationType: String,
    val state: String,
    val failureReason: String?,
    val createdAt: String,
    val assets: List<Any>?,
    val model: String,
    val request: GenerationRequest
)

data class GenerationRequest(
    val generationType: String,
    val prompt: String,
    val aspectRatio: String,
    val loop: Boolean,
    val keyframes: Map<String, Keyframe?>,
    val callbackUrl: String?
)

data class Keyframe(
    val type: String,
    val url: String
)