package com.video.maker.dark.channel.controller

import com.video.maker.dark.channel.service.VideoService
import com.video.maker.dark.channel.service.impl.VideoServiceImpl
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/videos")
class VideoController(
    private val videoService: VideoService
) {

    @PostMapping
    fun create(@RequestBody prompt: String): List<String> {
        return videoService.create(prompt)
    }
}