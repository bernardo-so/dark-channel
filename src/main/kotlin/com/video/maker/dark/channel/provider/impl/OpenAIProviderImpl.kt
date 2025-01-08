package com.video.maker.dark.channel.provider.impl

import com.video.maker.dark.channel.provider.OpenAIProvider
import com.video.maker.dark.channel.vo.request.Message
import com.video.maker.dark.channel.vo.request.OpenAIRequest
import com.video.maker.dark.channel.vo.response.OpenAIResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Component
class OpenAIProviderImpl(
    @Value("\${openai.api.key}")
    private val key: String,

    @Value("\${openai.api.url}")
    private val url: String,

    @Value("\${openai.api.model}")
    private val model: String,

    @Value("\${openai.api.developer}")
    private val developer: String,

    private var restTemplate: RestTemplate
): OpenAIProvider {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun callApi(theme: String): String {
        logger.info("c=OpenAIProvider, m=callApi, i=Calling ChatGTP API, status=started")

        return try {
            val response = restTemplate.exchange(
                "$url/chat/completions",
                HttpMethod.POST,
                createRequest(theme),
                OpenAIResponse::class.java
            )
            response.body?.choices?.firstOrNull()?.message?.content ?: "Sem resposta"
        } catch (e: HttpClientErrorException) {
            throw e.also {
                logger.info("c=OpenAIProvider, m=callApi, i=Calling ChatGTP API, status=error, code=${e.statusCode} body=${e.responseBodyAsString}")
            }
        }
    }

    override fun createRequest(theme: String): HttpEntity<OpenAIRequest> {
        val request = OpenAIRequest(
            model = model,
            messages = listOf(
                Message(role = "developer", content = developer),
                Message(role = "user", content = theme)
            )
        )

        val headers = HttpHeaders().apply {
            set("Authorization", key)
            contentType = MediaType.APPLICATION_JSON
        }

        return HttpEntity(request, headers)
    }
}
