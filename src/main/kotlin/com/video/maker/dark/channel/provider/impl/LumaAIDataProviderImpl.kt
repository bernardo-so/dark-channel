package com.video.maker.dark.channel.provider.impl

import com.video.maker.dark.channel.provider.LumaAIDataProvider
import com.video.maker.dark.channel.vo.request.Frame
import com.video.maker.dark.channel.vo.request.Keyframes
import com.video.maker.dark.channel.vo.request.LumaAIRequest
import com.video.maker.dark.channel.vo.response.LumaAIResponse
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

    override fun createRequest(prompt: String, urls: List<String>): HttpEntity<LumaAIRequest>? =
        HttpEntity(
            LumaAIRequest(
                prompt = prompt,
                keyframes = Keyframes(
                    frame0 = Frame("image", urls[0]),
                    frame1 = Frame("image", urls[0])
                )
            ),
            createHeaders()
        )


    override fun generateVideo(prompt: String, urls: List<String>): String {

        return runCatching {
            restTemplate.exchange(
                "$url/generations",
                HttpMethod.POST,
                createRequest(prompt, urls),
                LumaAIResponse::class.java
            )
        }.onSuccess {
            logger.info("c=LumaAIDataProvider, m=generateVideo, i=Success calling LumaAI")
        }.onFailure { ex ->
            throw ex.also {
                logger.info("c=LeonardoAIProvider, m=callApi, status=error, e=${ex.javaClass.simpleName}, message=${ex.localizedMessage}")
            }
        }.getOrThrow().body!!.id
    }

}