package com.video.maker.dark.channel.vo.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LumaAIRequest(
    val prompt: String,
    @SerialName("aspect_ratio") val aspectRatio: String = "9:16",
    val keyframes: Keyframes
)

@Serializable
data class Frame(
    val type: String = "image",
    val url: String
)

@Serializable
data class Keyframes(
    val frame0: Frame,
    val frame1: Frame
)
