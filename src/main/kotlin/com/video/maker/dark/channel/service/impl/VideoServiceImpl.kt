package com.video.maker.dark.channel.service.impl

import com.video.maker.dark.channel.service.LeonardoAIService
import com.video.maker.dark.channel.service.OpenAIService
import com.video.maker.dark.channel.service.VideoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class VideoServiceImpl(
    private val leonardoAIService: LeonardoAIService,
    private val openAIService: OpenAIService,
    private val videoService: EditorServiceImpl,
) : VideoService {

    private val logger: Logger = LoggerFactory.getLogger(VideoService::class.java)

    override fun create(theme: String): Boolean {
        openAIService.getPrompt(theme)?.let {
            getImages(it).also { editVideo() }
            return true
        }

        return false
    }

    override fun getImages(prompt: String) {
        runCatching {
            leonardoAIService.getImages(prompt).also {
                logger.info(" c=VideoService, m=getImages, i=Getting images from LeonardoAI, status=started")
            }
        }.onSuccess {
            logger.info(" c=VideoService, m=getImages, i=Success getting images from LeonardoAI, status=finished")
        }.onFailure {
            throw it.also {
                logger.info(" c=VideoService, m=getImages, i=Failed getting an images From LeonardoAI, status=error")
            }
        }
    }

    override fun editVideo() {
        videoService.generateVideo()
    }

}
