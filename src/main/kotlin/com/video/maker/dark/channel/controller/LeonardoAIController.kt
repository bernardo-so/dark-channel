package com.video.maker.dark.channel.controller

import com.video.maker.dark.channel.provider.impl.LeonardoAIDataProviderImpl
import com.video.maker.dark.channel.service.LeonardoAIService
import com.video.maker.dark.channel.vo.response.LeonardoAIGetImagesResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/leonardoai")
class LeonardoAIController(
    private val leonardoAIService: LeonardoAIService,
    private val leonardoAIProviderImpl: LeonardoAIDataProviderImpl
) {
    @PostMapping("/image")
    fun generate(@RequestBody prompt: String): List<String?>? {
        return leonardoAIService.getImages(prompt)
    }

    @PostMapping("/get-image")
    fun getImage(): LeonardoAIGetImagesResponse {
        return leonardoAIProviderImpl.getImage("f4e2b91e-1a7c-4402-81dc-3d31037b1b5c")
    }
}