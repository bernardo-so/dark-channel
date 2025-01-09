package com.video.maker.dark.channel.service.impl

import com.video.maker.dark.channel.service.LeonardoAIService
import com.video.maker.dark.channel.service.LumaAIService
import com.video.maker.dark.channel.service.OpenAIService
import com.video.maker.dark.channel.service.VideoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class VideoServiceImpl(
    private val lumaAIService: LumaAIService,
    private val leonardoAIService: LeonardoAIService,
    private val openAIService: OpenAIService,
    private val videoService: EditorServiceImpl,
    @Value("\${openai.api.prompt1}")
    private val prompt1: String,
    @Value("\${openai.api.prompt2}")
    private val prompt2: String
) : VideoService {

    private val logger: Logger = LoggerFactory.getLogger(VideoService::class.java)

    override fun create(theme: String): String {
        val prompt = openAIService.getPrompt(theme, prompt1).also {
            logger.info("c=VideoServiceImpl, m=create, theme=$theme")
        }

        val images = leonardoAIService.getImages(prompt).also {
            logger.info("c=VideoServiceImpl, m=create, prompt=$prompt")
        }

        val promptForLuma = openAIService.getPrompt(prompt, prompt2).also {
            logger.info("c=VideoServiceImpl, m=create, promptForLuma=$it")
        }

        val lumaAIService = lumaAIService.getVideo(promptForLuma, images)

        return lumaAIService
    }

    override fun getImages(prompt: String): List<String> {
        return leonardoAIService.getImages(prompt)
    }

    override fun editVideo() {
        videoService.generateVideo()
    }

}
