package com.video.maker.dark.channel.service.impl

import com.video.maker.dark.channel.provider.OpenAIProvider
import com.video.maker.dark.channel.service.OpenAIService
import com.video.maker.dark.channel.service.VideoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OpenAIServiceImpl(
    private val openAIProvider: OpenAIProvider
): OpenAIService {
    private val logger: Logger = LoggerFactory.getLogger(VideoService::class.java)

    override fun getPrompt(theme: String): String? {
        runCatching {
            openAIProvider.getPrompt(theme).also {
                logger.info(" c=VideoService, m=getPrompt, i=Getting a prompt from ChatGPT, status=started")
            }
        }.onSuccess {
            return it.also {
                logger.info(" c=VideoService, m=getPrompt, i=Success getting a prompt from ChatGPT, status=finished")
            }
        }.onFailure {
            throw it.also { logger.info(" c=VideoService, m=getPrompt, i=Failed getting a prompt from ChatGPT, status=error") }
        }

        return null
    }
}