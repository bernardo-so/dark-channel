package com.video.maker.dark.channel.service

interface OpenAIService {
    fun getPrompt(theme: String, prompt: String): String
}