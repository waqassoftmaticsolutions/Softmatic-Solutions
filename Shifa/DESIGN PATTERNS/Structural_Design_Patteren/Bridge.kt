interface VideoProcessor {
  fun process(videoFile: String)
}

class HDProcessor : VideoProcessor {
  override fun process(videoFile: String) {
      // Process HD video file
      println("Processing HD video file: $videoFile")
  }
}

class UHD4KProcessor : VideoProcessor {
  override fun process(videoFile: String) {
      // Process UHD 4K video file
      println("Processing UHD 4K video file: $videoFile")
  }
}

abstract class Video(var processor: VideoProcessor) {
  abstract fun play(videoFile: String)
}

class NetflixVideo(processor: VideoProcessor) : Video(processor) {
  override fun play(videoFile: String) {
      processor.process(videoFile)
  }
}

fun main() {
  val hdProcessor = HDProcessor()
  val uhd4KProcessor = UHD4KProcessor()

  val netflixHDVideo = NetflixVideo(hdProcessor)
  netflixHDVideo.play("hd_video.mp4")

  val netflixUHDVideo = NetflixVideo(uhd4KProcessor)
  netflixUHDVideo.play("uhd_video.mp4")
}
