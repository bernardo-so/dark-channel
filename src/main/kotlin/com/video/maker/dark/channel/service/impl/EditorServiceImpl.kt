package com.video.maker.dark.channel.service.impl

import org.springframework.stereotype.Service
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.StandardCopyOption

@Service
class EditorServiceImpl {
//    private fun executeFFmpegCommand(command: String) {
//        val process = ProcessBuilder(*command.split(" ").toTypedArray())
//            .redirectErrorStream(true)
//            .start()
//
//        process.inputStream.bufferedReader().useLines { lines ->
//            lines.forEach { println(it) }
//        }
//
//        val exitCode = process.waitFor()
//        if (exitCode != 0) {
//            throw RuntimeException("FFmpeg command failed with exit code $exitCode")
//        }
//    }
//
//    private fun createSmoothBlurTransition(inputVideo: String, outputTransition: String, duration: Int = 2) {
//        val blurCommand = """
//        ffmpeg -i "$inputVideo" -vf "boxblur=luma_radius=10:luma_power=3,fade=t=in:st=0:d=${duration / 2},fade=t=out:st=${duration / 2}:d=${duration / 2}" -an -t $duration "$outputTransition"
//    """.trimIndent()
//
//        println("Criando transição com blur suave...")
//        executeFFmpegCommand(blurCommand)
//    }
//
//
//    fun generateVideo() {
//        val animation1 = "C:\\Projetos Pessoais\\img\\imagem.mp4"
//        val audioPath = "C:\\Projetos Pessoais\\audio\\audio.mp3"
//        val outputConcat = "C:\\Projetos Pessoais\\img\\output_concatenated.mp4"
//        val outputFinal = "C:\\Projetos Pessoais\\img\\final_video.mp4"
//
//        val blur1 = "C:\\Projetos Pessoais\\img\\blur1.mp4"
//        val blur2 = "C:\\Projetos Pessoais\\img\\blur2.mp4"
//        val fileList = File("C:\\Projetos Pessoais\\img\\file_list.txt")
//
//        // Criar transições suaves com blur
//        createSmoothBlurTransition(animation1, blur1)
//        createSmoothBlurTransition(animation1, blur2)
//
//        // Criar lista de vídeos com transições
//        fileList.writeText(
//            """
//        file '$animation1'
//        file '$blur1'
//        file '$animation1'
//        file '$blur2'
//        file '$animation1'
//        """.trimIndent()
//        )
//
//        // Concatenar vídeos e transições
//        val concatCommand = """
//        ffmpeg -f concat -safe 0 -i "${fileList.path}" -c copy "$outputConcat"
//    """.trimIndent()
//
//        println("Concatenando vídeos com transições suaves...")
//        executeFFmpegCommand(concatCommand)
//
//        // Adicionar áudio ao vídeo concatenado
//        val addAudioCommand = """
//        ffmpeg -i "$outputConcat" -i "$audioPath" -c:v copy -c:a aac -shortest "$outputFinal"
//    """.trimIndent()
//
//        println("Adicionando áudio ao vídeo...")
//        executeFFmpegCommand(addAudioCommand)
//
//        println("Vídeo final gerado em: $outputFinal")
//    }

    fun executeFFmpegCommand(urls: List<String>) {
        val videoUrls = listOf(
            "https://url-exemplo.com/video1.mp4",
            "https://url-exemplo.com/video2.mp4",
            "https://url-exemplo.com/video3.mp4",
            "https://url-exemplo.com/video4.mp4",
            "https://url-exemplo.com/video5.mp4",
            "https://url-exemplo.com/video6.mp4",
            "https://url-exemplo.com/video7.mp4",
            "https://url-exemplo.com/video8.mp4",
            "https://url-exemplo.com/video9.mp4",
            "https://url-exemplo.com/video10.mp4"
        )
        val startTime = "00:00:00" // Hora de início no formato HH:mm:ss
        val duration = 5 // Duração em segundos do clipe

        videoUrls.forEachIndexed { index, videoUrl ->
            try {
                // Defina o nome do arquivo de saída para cada vídeo
                val outputVideoPath = "output_short_video_${index + 1}.mp4"

                // Baixe o vídeo
                val tempVideoFile = File("temp_video_${index + 1}.mp4")
                downloadFile(videoUrl, tempVideoFile)

                // Crie o clipe curto
                createShortClip(tempVideoFile, outputVideoPath, startTime, duration)

                println("Vídeo curto criado com sucesso: $outputVideoPath")
            } catch (e: Exception) {
                println("Erro ao processar o vídeo na URL $videoUrl: ${e.message}")
            }
        }
    }

    /**
     * Baixa um arquivo de uma URL e salva em um arquivo temporário.
     */
    fun downloadFile(fileUrl: String, outputFile: File) {
        URL(fileUrl).openStream().use { inputStream ->
            Files.copy(inputStream, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
        }
        println("Arquivo baixado com sucesso: ${outputFile.absolutePath}")
    }

    /**
     * Cria um clipe curto a partir de um vídeo usando FFmpeg.
     */
    fun createShortClip(inputVideoFile: File, outputVideoPath: String, startTime: String, duration: Int) {
        val ffmpegCommand = listOf(
            "ffmpeg",
            "-i", inputVideoFile.absolutePath, // Arquivo de entrada
            "-ss", startTime, // Hora de início do clipe
            "-t", duration.toString(), // Duração do clipe
            "-c:v", "copy", // Copiar codec de vídeo (sem recodificação)
            "-c:a", "copy", // Copiar codec de áudio (sem recodificação)
            "-y", // Sobrescrever o arquivo de saída se já existir
            outputVideoPath // Caminho do arquivo de saída
        )

        val processBuilder = ProcessBuilder(ffmpegCommand)
        val process = processBuilder.inheritIO().start()
        val exitCode = process.waitFor()

        if (exitCode != 0) {
            throw RuntimeException("Erro ao executar o FFmpeg. Código de saída: $exitCode")
        }
    }

}

