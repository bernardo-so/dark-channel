package com.video.maker.dark.channel.service

interface VideoService {
    fun create(theme: String): Boolean
    fun getImages(prompt: String)
    fun editVideo()
}