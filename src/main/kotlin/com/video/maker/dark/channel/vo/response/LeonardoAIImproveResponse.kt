package com.video.maker.dark.channel.vo.response

data class LeonardoAIImproveResponse(
    val promptGeneration: PromptGeneration
)

data class PromptGeneration(
    val prompt: String,
    val apiCreditCost: Int
)
