package com.video.maker.dark.channel.vo.response

data class LeonardoAIGetImagesResponse(
    val generationsByPk: GenerationsByPk
)

data class GenerationsByPk(
    val generatedImages: List<GeneratedImage>,
    val modelId: String,
    val motion: String?,
    val motionModel: String?,
    val motionStrength: Double?,
    val prompt: String,
    val negativePrompt: String,
    val imageHeight: Int,
    val imageToVideo: String?,
    val imageWidth: Int,
    val inferenceSteps: Int,
    val seed: Long,
    val ultra: Boolean?,
    val `public`: Boolean,
    val scheduler: String,
    val sdVersion: String,
    val status: String,
    val presetStyle: String?,
    val initStrength: Double?,
    val guidanceScale: Double,
    val id: String,
    val createdAt: String,
    val promptMagic: Boolean,
    val promptMagicVersion: String?,
    val promptMagicStrength: Double?,
    val photoReal: Boolean,
    val photoRealStrength: Double?,
    val fantasyAvatar: String?,
    val promptModerations: List<PromptModeration>,
    val generationElements: List<Any>
)

data class GeneratedImage(
    val url: String,
    val nsfw: Boolean,
    val id: String,
    val likeCount: Int,
    val motionMP4URL: String?,
    val generatedImageVariationGenerics: List<String>
)

data class PromptModeration(
    val moderationClassification: List<String>
)