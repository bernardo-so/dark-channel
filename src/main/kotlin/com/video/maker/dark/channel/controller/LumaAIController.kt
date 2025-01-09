package com.video.maker.dark.channel.controller

import com.video.maker.dark.channel.service.LumaAIService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lumaai")
class LumaAIController(
    private val lumaAIService: LumaAIService
) {
    @GetMapping("/prompt")
    fun create(): String? {
        return lumaAIService.createVideo("A penguin", "https://cdn.leonardo.ai/users/83facb9e-ff93-4f77-ab9f-c06cb65f783e/generations/814b66d5-c445-473a-9f36-42a061f16cc9/Default_A_charmingly_quirky_penguin_waddles_on_an_icy_landscap_0.jpg")
    }

    @GetMapping("/url")
    fun getImagePrompt(): String? {
        return lumaAIService.getVideoUrl("f7797a43-93ac-46d4-a3c5-7e5059de659c")
    }
}