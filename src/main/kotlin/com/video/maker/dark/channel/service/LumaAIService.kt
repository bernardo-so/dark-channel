package com.video.maker.dark.channel.service

interface LumaAIService {
    fun getVideo(prompt:String, urls: List<String>): String
}