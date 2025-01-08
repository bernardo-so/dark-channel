package com.video.maker.dark.channel.vo.response

data class LeonardAIResponse(
    val sdGenerationJob: SdGenerationJob
)

data class SdGenerationJob(
    val generationId: String,
    val apiCreditCost: Int
)
