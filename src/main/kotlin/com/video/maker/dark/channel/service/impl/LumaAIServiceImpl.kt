package com.video.maker.dark.channel.service.impl

import com.video.maker.dark.channel.provider.LumaAIDataProvider
import com.video.maker.dark.channel.service.LumaAIService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LumaAIServiceImpl(
    private val lumaAIDataProvider: LumaAIDataProvider
): LumaAIService {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun createVideo(prompt:String, url: String): String {
        return lumaAIDataProvider.generateVideo(prompt, url)
    }

    override fun getVideoUrl(uuid: String): String {
        return lumaAIDataProvider.getVideo(uuid)
    }
}