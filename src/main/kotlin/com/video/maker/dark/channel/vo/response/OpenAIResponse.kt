package com.video.maker.dark.channel.vo.response

import com.video.maker.dark.channel.vo.request.Message

data class OpenAIResponse (
    var choices: List<Choice?>? = mutableListOf()
)

data class Choice (
    var index: Int = 0,
    var message: Message? = null
)