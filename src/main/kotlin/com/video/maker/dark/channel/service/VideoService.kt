package com.video.maker.dark.channel.service

interface VideoService {
    fun create(theme: String): String
    fun getImages(prompt: String): List<String>
    fun editVideo()
}