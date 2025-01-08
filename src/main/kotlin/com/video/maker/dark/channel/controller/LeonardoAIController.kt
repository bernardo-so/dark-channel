package com.video.maker.dark.channel.controller

import com.video.maker.dark.channel.service.LeonardoAIService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/leonardoai")
class LeonardoAIController(
    private val leonardoAIService: LeonardoAIService
) {
    @PostMapping("/image")
    fun generate(@RequestBody prompt: String): String? {
        return leonardoAIService.getImages(prompt)
    }
}