package com.video.maker.dark.channel.controller

import com.video.maker.dark.channel.service.OpenAIService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/openai")
class OpenAIController(
    private val openAIService: OpenAIService
) {
    @GetMapping("/prompt")
    fun create(@RequestBody theme: String): String? {
        return openAIService.getPrompt(theme)
    }
}