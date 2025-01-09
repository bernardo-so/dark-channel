package com.video.maker.dark.channel.vo.request

import com.fasterxml.jackson.annotation.JsonProperty

data class LumaAIRequest(
    val prompt: String,
    @JsonProperty("aspect_ratio")
    val aspectRatio: String = "9:16",
    val keyframes: Map<String, Frame>
)

data class Frame(
    val type: String = "image",
    val url: String
)
