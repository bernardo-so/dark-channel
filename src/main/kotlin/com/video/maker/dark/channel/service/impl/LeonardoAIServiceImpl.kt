package com.video.maker.dark.channel.service.impl

import com.video.maker.dark.channel.provider.LeonardoAIProvider
import com.video.maker.dark.channel.service.LeonardoAIService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LeonardoAIServiceImpl(
    private val leonardoAIProvider: LeonardoAIProvider
): LeonardoAIService {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun getImages(prompt: String) {
        runCatching {
            leonardoAIProvider.getImages(prompt).also {
                logger.info("c=VideoService, m=getImages, i=Getting images from LeonardoAI, status=started")
            }
        }.onSuccess {
            logger.info("c=VideoService, m=getImages, i=Success getting images from LeonardoAI, status=finished")
        }.onFailure {
            throw it.also {
                logger.info("c=VideoService, m=getImages, i=Failed getting an images From LeonardoAI, status=error")
            }
        }
    }
}