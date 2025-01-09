package com.video.maker.dark.channel.provider

import com.video.maker.dark.channel.vo.request.LumaAIRequest
import org.springframework.http.HttpEntity

interface LumaAIDataProvider {
    fun createRequest(prompt: String,  url: String): HttpEntity<LumaAIRequest>?
    fun generateVideo(prompt: String,  imageURL: String): String
    fun getVideo(uuid: String): String
}