package com.video.maker.dark.channel.provider

import com.video.maker.dark.channel.vo.request.OpenAIRequest
import org.springframework.http.HttpEntity

interface OpenAIDataProvider {
    fun createRequest(theme: String, prompt: String): HttpEntity<OpenAIRequest>
    fun callApi(theme: String, prompt: String): String
}