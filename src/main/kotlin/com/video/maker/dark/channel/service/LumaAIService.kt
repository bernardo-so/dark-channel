package com.video.maker.dark.channel.service

interface LumaAIService {
    fun createVideo(prompt:String, url: String): String
    fun getVideoUrl(uuid: String): String
}