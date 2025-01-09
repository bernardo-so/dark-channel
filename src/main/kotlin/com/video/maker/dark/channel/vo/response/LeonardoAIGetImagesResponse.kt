package com.video.maker.dark.channel.vo.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeonardoAIGetImagesResponse(
    @SerialName("generations_by_pk") val generationsByPk: GenerationsByPk
)

@Serializable
data class GenerationsByPk(
    @SerialName("generated_images") val generatedImages: List<GeneratedImage>,
    @SerialName("modelId") val modelId: String?,
    @SerialName("motion") val motion: String?,
    @SerialName("motionModel") val motionModel: String?,
    @SerialName("motionStrength") val motionStrength: String?,
    @SerialName("prompt") val prompt: String?,
    @SerialName("negativePrompt") val negativePrompt: String?,
    @SerialName("imageHeight") val imageHeight: Int?,
    @SerialName("imageToVideo") val imageToVideo: String?,
    @SerialName("imageWidth") val imageWidth: Int?,
    @SerialName("inferenceSteps") val inferenceSteps: Int?,
    @SerialName("seed") val seed: Long?,
    @SerialName("ultra") val ultra: String?,
    @SerialName("public") val isPublic: Boolean?,
    @SerialName("scheduler") val scheduler: String?,
    @SerialName("sdVersion") val sdVersion: String?,
    @SerialName("status") val status: String?,
    @SerialName("presetStyle") val presetStyle: String?,
    @SerialName("initStrength") val initStrength: String?,
    @SerialName("guidanceScale") val guidanceScale: Double?,
    @SerialName("id") val id: String?,
    @SerialName("createdAt") val createdAt: String?,
    @SerialName("promptMagic") val promptMagic: Boolean?,
    @SerialName("promptMagicVersion") val promptMagicVersion: String?,
    @SerialName("promptMagicStrength") val promptMagicStrength: String?,
    @SerialName("photoReal") val photoReal: Boolean?,
    @SerialName("photoRealStrength") val photoRealStrength: String?,
    @SerialName("fantasyAvatar") val fantasyAvatar: String?,
    @SerialName("prompt_moderations") val promptModerations: List<PromptModeration>?,
    @SerialName("generation_elements") val generationElements: List<String>?
)

@Serializable
data class GeneratedImage(
    @SerialName("url") val url: String,
    @SerialName("nsfw") val nsfw: Boolean?,
    @SerialName("id") val id: String?,
    @SerialName("likeCount") val likeCount: Int?,
    @SerialName("motionMP4URL") val motionMp4Url: String?,
    @SerialName("generated_image_variation_generics") val generatedImageVariationGenerics: List<String>?
)

@Serializable
data class PromptModeration(
    @SerialName("moderationClassification") val moderationClassification: List<String>?
)
