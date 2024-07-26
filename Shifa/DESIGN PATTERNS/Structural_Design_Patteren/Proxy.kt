//The Proxy design pattern is a structural pattern that provides a surrogate or placeholder for another object to control access to it

//Subject interface
interface Video{
  fun play()
}

//RealSubject
class RealVideo( val fileName:String):Video{
  init{
    loadFromDisk()
  }
  private fun loadFromDisk(){
    println("Loading video from disk...")
  }
  override fun play(){
    println("playing video : $fileName")
  }
}
class VideoProxy(val fileName:String):Video{
  private var realVideo:RealVideo?=null
  override fun play(){
    if(realVideo==null){
      realVideo=RealVideo(fileName)
    }
    realVideo?.play()
  }
  
}
//Client code
fun main(){
  val video1=VideoProxy("video1.mp4")
  video1.play()

  val video2=VideoProxy("video2.mp4")
  video2.play()
  println("Again playing video 1")
  video1.play()
}
