package com.video.maker.dark.channel.service.impl

import com.video.maker.dark.channel.provider.impl.OpenAIDataProviderImpl
import com.video.maker.dark.channel.service.OpenAIService
import com.video.maker.dark.channel.service.VideoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OpenAIServiceImpl(
    private val openAIProviderImpl: OpenAIDataProviderImpl
): OpenAIService {
    private val logger: Logger = LoggerFactory.getLogger(VideoService::class.java)

    override fun getPrompt(theme: String, prompt: String): String {
        runCatching {
            return openAIProviderImpl.callApi(theme, prompt).also {
                logger.info(" c=VideoService, m=getPrompt, i=Getting a prompt from ChatGPT, status=started")
            }
        }.onSuccess {
            logger.info(" c=VideoService, m=getPrompt, i=Success getting a prompt from ChatGPT, status=finished")
        }.onFailure {
            logger.info(" c=VideoService, m=getPrompt, i=Failed getting a prompt from ChatGPT, status=error")
        }.getOrElse {
            throw Exception()
        }
    }
}