package com.video.maker.dark.channel.provider

import com.video.maker.dark.channel.vo.request.OpenAIRequest
import org.springframework.http.HttpEntity

interface OpenAIProvider {
    fun createRequest(theme: String): HttpEntity<OpenAIRequest>
    fun callApi(theme: String): String
}