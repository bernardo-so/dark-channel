package com.video.maker.dark.channel.service.impl

import org.springframework.stereotype.Service
import java.io.File

@Service
class EditorServiceImpl {
    private fun executeFFmpegCommand(command: String) {
        val process = ProcessBuilder(*command.split(" ").toTypedArray())
            .redirectErrorStream(true)
            .start()

        process.inputStream.bufferedReader().useLines { lines ->
            lines.forEach { println(it) }
        }

        val exitCode = process.waitFor()
        if (exitCode != 0) {
            throw RuntimeException("FFmpeg command failed with exit code $exitCode")
        }
    }

    private fun createSmoothBlurTransition(inputVideo: String, outputTransition: String, duration: Int = 2) {
        val blurCommand = """
        ffmpeg -i "$inputVideo" -vf "boxblur=luma_radius=10:luma_power=3,fade=t=in:st=0:d=${duration / 2},fade=t=out:st=${duration / 2}:d=${duration / 2}" -an -t $duration "$outputTransition"
    """.trimIndent()

        println("Criando transição com blur suave...")
        executeFFmpegCommand(blurCommand)
    }


    fun generateVideo() {
        val animation1 = "C:\\Projetos Pessoais\\img\\imagem.mp4"
        val audioPath = "C:\\Projetos Pessoais\\audio\\audio.mp3"
        val outputConcat = "C:\\Projetos Pessoais\\img\\output_concatenated.mp4"
        val outputFinal = "C:\\Projetos Pessoais\\img\\final_video.mp4"

        val blur1 = "C:\\Projetos Pessoais\\img\\blur1.mp4"
        val blur2 = "C:\\Projetos Pessoais\\img\\blur2.mp4"
        val fileList = File("C:\\Projetos Pessoais\\img\\file_list.txt")

        // Criar transições suaves com blur
        createSmoothBlurTransition(animation1, blur1)
        createSmoothBlurTransition(animation1, blur2)

        // Criar lista de vídeos com transições
        fileList.writeText(
            """
        file '$animation1'
        file '$blur1'
        file '$animation1'
        file '$blur2'
        file '$animation1'
        """.trimIndent()
        )

        // Concatenar vídeos e transições
        val concatCommand = """
        ffmpeg -f concat -safe 0 -i "${fileList.path}" -c copy "$outputConcat"
    """.trimIndent()

        println("Concatenando vídeos com transições suaves...")
        executeFFmpegCommand(concatCommand)

        // Adicionar áudio ao vídeo concatenado
        val addAudioCommand = """
        ffmpeg -i "$outputConcat" -i "$audioPath" -c:v copy -c:a aac -shortest "$outputFinal"
    """.trimIndent()

        println("Adicionando áudio ao vídeo...")
        executeFFmpegCommand(addAudioCommand)

        println("Vídeo final gerado em: $outputFinal")
    }
}

