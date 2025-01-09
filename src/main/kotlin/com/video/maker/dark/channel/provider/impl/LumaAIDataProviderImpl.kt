package com.video.maker.dark.channel.provider.impl

import com.video.maker.dark.channel.provider.LumaAIDataProvider
import com.video.maker.dark.channel.vo.request.Frame
import com.video.maker.dark.channel.vo.request.LumaAIRequest
import com.video.maker.dark.channel.vo.response.LumaAIGetImageRequest
import com.video.maker.dark.channel.vo.response.LumaAIResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class LumaAIDataProviderImpl  (
    @Value("\${lumaai.api.key}") private val key: String,
    @Value("\${lumaai.api.url}") private val url: String,
    private val restTemplate: RestTemplate
) : LumaAIDataProvider {

    private val logger = LoggerFactory.getLogger(javaClass)

    private fun createHeaders(): HttpHeaders = HttpHeaders().apply {
        set("Authorization", key)
        contentType = MediaType.APPLICATION_JSON
    }

    override fun createRequest(prompt: String,  url: String): HttpEntity<LumaAIRequest> {
        logger.info("c=LumaAIDataProvider, m=createRequest, prompt=$prompt !")

        val keyframes = if (url.isNotEmpty()) {
            mapOf("frame0" to Frame(url = url))
        } else {
            emptyMap()
        }

        val requestBody = LumaAIRequest(
            prompt = prompt,
            keyframes = keyframes
        )

        return HttpEntity(requestBody, createHeaders())
    }

    override fun generateVideo(prompt: String, imageURL: String): String {
        return runCatching {
            restTemplate.exchange(
                "$url/generations",
                HttpMethod.POST,
                createRequest(prompt, imageURL),
                LumaAIResponse::class.java
            )
        }.onSuccess {
            logger.info("c=LumaAIDataProvider, m=generateVideo, i=Success generating video !")
        }.onFailure { ex ->
            throw ex.also {
                logger.info("c=LumaAIDataProvider, m=generateVideo, status=error, e=${ex.javaClass.simpleName}, message=${ex.localizedMessage}")
            }
        }.getOrThrow().body!!.id
    }

    override fun getVideo(uuid: String): String {
        waitForLuma()
        return runCatching {
            restTemplate.exchange(
                "$url/generations/$uuid",
                HttpMethod.GET,
                HttpEntity(null, createHeaders()),
                LumaAIGetImageRequest::class.java
            )
        }.onSuccess {
            logger.info("c=LumaAIDataProvider, m=getVideo, i=Success getting video !")
        }.onFailure { ex ->
            throw ex.also {
                logger.info("c=LumaAIDataProvider, m=getVideo, status=error, e=${ex.javaClass.simpleName}, message=${ex.localizedMessage}")
            }
        }.getOrThrow().body!!.assets.video
    }

    private fun waitForLuma (){
        runBlocking {
            val totalDuration = 60_000L
            val steps = 10
            val stepDuration = totalDuration / steps

            repeat(steps) { stepNumber ->
                delay(stepDuration)
                logger.info("Waiting to get the image from LumaAI ... ${(stepNumber + 1) * 10}%")
            }

            logger.info("Calling LumaAI !")
        }
    }

}