package com.video.maker.dark.channel.vo.response

import com.fasterxml.jackson.annotation.JsonProperty

data class LeonardoAIGetImagesResponse(
    @JsonProperty("generations_by_pk")
    val generationsByPk: GenerationsByPk
)

data class GenerationsByPk(
    @JsonProperty("generated_images") val
    generatedImages: List<GeneratedImage>,
    val modelId: String?,
    val motion: String?,
    val motionModel: String?,
    val motionStrength: String?,
    val prompt: String?,
    val negativePrompt: String?,
    val imageHeight: Int?,
    val imageToVideo: String?,
    val imageWidth: Int?,
    val inferenceSteps: Int?,
    val seed: Long?,
    val ultra: String?,
    @JsonProperty("public")
    val isPublic: Boolean?,
    val scheduler: String?,
    val sdVersion: String?,
    val status: String?,
    val presetStyle: String?,
    val initStrength: String?,
    val guidanceScale: Double?,
    val id: String?,
    val createdAt: String?,
    val promptMagic: Boolean?,
    val promptMagicVersion: String?,
    val promptMagicStrength: String?,
    val photoReal: Boolean?,
    val photoRealStrength: String?,
    val fantasyAvatar: String?,
    @JsonProperty("prompt_moderations")
    val promptModerations: List<PromptModeration>?,
    @JsonProperty("generation_elements")
    val generationElements: List<String>?
)

data class GeneratedImage(
    val url: String,
    val nsfw: Boolean?,
    val id: String?,
    val likeCount: Int?,
    @JsonProperty("motionMP4URL")
    val motionMp4Url: String?,
    @JsonProperty("generated_image_variation_generics")
    val generatedImageVariationGenerics: List<String>?
)

data class PromptModeration(
    val moderationClassification: List<String>?
)
