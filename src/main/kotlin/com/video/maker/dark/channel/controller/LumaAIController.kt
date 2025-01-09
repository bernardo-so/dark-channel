package com.video.maker.dark.channel.controller

import com.video.maker.dark.channel.service.LumaAIService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lumaai")
class LumaAIController(
    private val lumaAIService: LumaAIService
) {
    @GetMapping("/prompt")
    fun create(): String? {
        return lumaAIService.getVideo("A penguin", listOf("https://cdn.leonardo.ai/users/83facb9e-ff93-4f77-ab9f-c06cb65f783e/generations/814b66d5-c445-473a-9f36-42a061f16cc9/Default_A_charmingly_quirky_penguin_waddles_on_an_icy_landscap_0.jpg"))
    }
}