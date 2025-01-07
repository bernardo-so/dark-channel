package com.video.maker.dark.channel.provider

import com.video.maker.dark.channel.service.impl.LeonardoAIServiceImpl
import com.video.maker.dark.channel.vo.request.ChatGptRequest
import com.video.maker.dark.channel.vo.request.Message
import com.video.maker.dark.channel.vo.response.OpenAIResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Component
class OpenAIProvider {

    private val logger: Logger = LoggerFactory.getLogger(OpenAIProvider::class.java)

    @Value("\${openai.api.key}")
    private val key: String = ""

    @Value("\${openai.api.url}")
    private val url: String = ""

    @Value("\${openai.api.model}")
    private val model: String = ""

    @Autowired
    private lateinit var restTemplate: RestTemplate

    fun getPrompt(prompt: String): String {
        val request = ChatGptRequest(
            model = model,
            messages = listOf(
                Message(role = "user", content = prompt)
            )
        )

        val headers = HttpHeaders().apply {
            set("Authorization", key)
            contentType = MediaType.APPLICATION_JSON
        }

        val entity = HttpEntity(request, headers)

        return try {
            val response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                OpenAIResponse::class.java
            )
            response.body?.choices?.firstOrNull()?.message?.content ?: "Sem resposta"
        } catch (e: HttpClientErrorException) {
            "Erro ao chamar a API: ${e.statusCode} - ${e.responseBodyAsString}"
        }
    }
}
