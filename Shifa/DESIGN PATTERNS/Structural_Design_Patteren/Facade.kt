
//The Facade design pattern is a structural pattern that provides a simplified interface to a complex subsystem, making it easier to use. 
//Facade: A class that provides a simple interface to the complex subsystem.
//Subsystem Classes: The classes that implement the complex functionality.
//Client: The client interacts with the Facade instead of the subsystem classes directly.

//Subsystem
class DvdPlayer{
  fun on(){
    println("DVD Player ON")
  }
  fun play(movie:String){
    println("Playing movier: $movie")
  }
  fun off(){
    println("DVD Player OFF")
  }
}
class Amplifier{
  fun on(){
    println("Amplifier is ON")
  }
  fun setVolume(level:Int){
    println("Setting voume to $level")
  }
  fun off(){
    println("Amplifier OFF")
  }
}
class Projector{
  fun on(){
    println("Projector ON")
  }
  fun wideScreenMode(){
    println("Projector is in wife screen Mode")
  }
  fun off(){
    println("Projector OFF")
  }
}

//Facade class
class HomeTheaterFacade(private var dvdPlayer:DvdPlayer,private var amplifier:Amplifier,private var projector:Projector){
  fun watchMovie(movie:String){
    println("Ready to watch movie...")
    println("")
    projector.on()
    projector.wideScreenMode()
    amplifier.on()
    amplifier.setVolume(5)
    dvdPlayer.on()
    dvdPlayer.play(movie)
  }
  fun endMovie(){
    println("")
    println("Shutting Movie theather down...")
    dvdPlayer.off()
    amplifier.off()
    projector.off()
    println("")
  }
}

//client(main)
fun main(){
  

val dvdPlayer=DvdPlayer()
val amplifier=Amplifier()
val projector=Projector()
val hometheater=HomeTheaterFacade(dvdPlayer,amplifier,projector)
hometheater.watchMovie("Smile")
hometheater.endMovie()
}
