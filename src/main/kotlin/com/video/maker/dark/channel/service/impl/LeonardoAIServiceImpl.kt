package com.video.maker.dark.channel.service.impl

import com.video.maker.dark.channel.provider.impl.LeonardoAIDataProviderImpl
import com.video.maker.dark.channel.service.LeonardoAIService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LeonardoAIServiceImpl(
    private val leonardoAIProviderImpl: LeonardoAIDataProviderImpl
) : LeonardoAIService {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun getImages(prompt: String): List<String> {
        logger.info("c=LeonardoAIServiceImpl, m=getImages, i=Getting images from LeonardoAI, status=started")

        return leonardoAIProviderImpl.generateImage(prompt).let {
            leonardoAIProviderImpl.getImage(it)
        }.generationsByPk.generatedImages.map { it.url }.also {
            logger.info("c=LeonardoAIServiceImpl, m=getImages, i=Success getting images from LeonardoAI, status=finished")
        }

    }
}