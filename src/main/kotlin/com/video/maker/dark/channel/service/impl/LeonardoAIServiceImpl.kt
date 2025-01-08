package com.video.maker.dark.channel.service.impl

import com.video.maker.dark.channel.provider.impl.LeonardoAIProviderImpl
import com.video.maker.dark.channel.service.LeonardoAIService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LeonardoAIServiceImpl(
    private val leonardoAIProviderImpl: LeonardoAIProviderImpl
): LeonardoAIService {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun getImages(prompt: String): String? = runCatching {
        leonardoAIProviderImpl.callApi(prompt).also {
            logger.info("c=LeonardoAIServiceImpl, m=getImages, i=Getting images from LeonardoAI, status=started")
        }
    }.onSuccess {
        logger.info("c=LeonardoAIServiceImpl, m=getImages, i=Success getting images from LeonardoAI, status=finished")
    }.onFailure {
        logger.error("c=LeonardoAIServiceImpl, m=getImages, i=Failed getting images from LeonardoAI, status=error", it)
    }.getOrNull()
}