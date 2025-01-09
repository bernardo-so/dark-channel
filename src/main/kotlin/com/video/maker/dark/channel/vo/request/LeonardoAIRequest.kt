package com.video.maker.dark.channel.vo.request

import com.fasterxml.jackson.annotation.JsonProperty

data class LeonardoAIRequest(
    val modelId: String = "1dd50843-d653-4516-a8e3-f0238ee453ff",
    val contrast: Double = 3.5,
    val prompt: String,
    @JsonProperty("num_images")
    val numImages: Int = 4,
    val width: Int = 768,
    val height: Int = 1368,
    val styleUUID: String = "111dc692-d470-4eec-b791-3475abac4c46",
    val enhancePrompt: Boolean = true
)