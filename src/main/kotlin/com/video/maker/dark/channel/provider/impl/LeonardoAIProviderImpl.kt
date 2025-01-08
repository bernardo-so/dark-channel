package com.video.maker.dark.channel.provider.impl

import com.video.maker.dark.channel.provider.LeonardoAIProvider
import com.video.maker.dark.channel.vo.request.LeonardoAIImproveRequest
import com.video.maker.dark.channel.vo.request.LeonardoAIRequest
import com.video.maker.dark.channel.vo.response.LeonardAIResponse
import com.video.maker.dark.channel.vo.response.LeonardoAIGetImagesResponse
import com.video.maker.dark.channel.vo.response.LeonardoAIImproveResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class LeonardoAIProviderImpl(
    @Value("\${leonardoai.api.key}") private val key: String,
    @Value("\${leonardoai.api.url}") private val url: String,
    private val restTemplate: RestTemplate
) : LeonardoAIProvider {

    private val logger = LoggerFactory.getLogger(javaClass)

    private fun createHeaders(): HttpHeaders = HttpHeaders().apply {
        set("Authorization", key)
        contentType = MediaType.APPLICATION_JSON
    }

    override fun createRequest(prompt: String): HttpEntity<LeonardoAIRequest> =
        improvePrompt(prompt)?.let { improvedPrompt ->
            HttpEntity(LeonardoAIRequest(prompt = improvedPrompt), createHeaders())
        } ?: throw IllegalArgumentException("Unable to improve prompt")

    override fun generateImage(prompt: String): String? {
        return runCatching {
            restTemplate.exchange(
                "$url/generations",
                HttpMethod.POST,
                createRequest(prompt),
                LeonardAIResponse::class.java
            )
        }.onSuccess {
            logger.info("c=LeonardoAIProvider, m=callApi, i=Success calling LeonardoAI API")
        }.onFailure { ex ->
            throw ex.also {
                logger.info("c=LeonardoAIProvider, m=callApi, status=error, e=${ex.javaClass.simpleName}, message=${ex.localizedMessage}")
            }
        }.getOrThrow().body!!.sdGenerationJob.generationId
    }

    override fun improvePrompt(prompt: String): String? {
        return runCatching {
            restTemplate.exchange(
                "$url/prompt/improve",
                HttpMethod.POST,
                HttpEntity(LeonardoAIImproveRequest(prompt), createHeaders()),
                LeonardoAIImproveResponse::class.java
            )
        }.onSuccess { response ->
            logger.info(
                "c=LeonardoAIProvider, m=improvePrompt, i=Success calling LeonardoAI API, improvedPrompt=${response.body?.promptGeneration?.prompt}"
            )
        }.onFailure { ex ->
            logger.info(
                "c=LeonardoAIProvider, m=improvePrompt, status=error, e=${ex.javaClass.simpleName}, message=${ex.localizedMessage}"
            )
        }.getOrThrow().body!!.promptGeneration.prompt
    }

    override fun getImage(uuid: String): LeonardoAIGetImagesResponse? {
        return runCatching {
            restTemplate.exchange(
                "$url/generations/$uuid",
                HttpMethod.POST,
                HttpEntity(null, createHeaders()),
                LeonardoAIGetImagesResponse::class.java
            )
        }.onSuccess { response ->
            logger.info(
                "c=LeonardoAIProvider, m=getImage, i=Success calling LeonardoAI API, improvedPrompt=${response.body?.generationsByPk?.id}"
            )
        }.onFailure { ex ->
            logger.info(
                "c=LeonardoAIProvider, m=getImage, status=error, e=${ex.javaClass.simpleName}, message=${ex.localizedMessage}"
            )
        }.getOrThrow().body!!
    }

}
