package com.video.maker.dark.channel.vo.request

data class LeonardoAIRequest(
    val modelId: String = "1dd50843-d653-4516-a8e3-f0238ee453ff",
    val contrast: Double = 3.5,
    val prompt: String,
    val num_images: Int = 4,
    val width: Int = 1472,
    val height: Int = 832,
    val styleUUID: String = "111dc692-d470-4eec-b791-3475abac4c46",
    val enhancePrompt: Boolean = true
)