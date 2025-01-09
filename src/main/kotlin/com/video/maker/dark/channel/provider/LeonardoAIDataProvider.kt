package com.video.maker.dark.channel.provider

import com.video.maker.dark.channel.vo.request.LeonardoAIRequest
import com.video.maker.dark.channel.vo.response.LeonardoAIGetImagesResponse
import org.springframework.http.HttpEntity

interface LeonardoAIDataProvider {
    fun createRequest(prompt: String): HttpEntity<LeonardoAIRequest>?
    fun generateImage(prompt: String): String?
    fun getImage(uuid: String): LeonardoAIGetImagesResponse?
    fun improvePrompt(prompt: String): String?
}