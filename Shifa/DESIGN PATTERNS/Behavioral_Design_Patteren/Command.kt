//The Command design pattern is a behavioral pattern used to encapsulate a request as an object, thereby allowing for 
//parameterization of clients with queues, requests, and operations. It also provides support for undoable operations.


//Command interface
interface Command{
  fun execute()
  fun undo()
}

//Receiver class
class Light{
  fun on(){
    println("Light is ON")
  }
  fun off(){
    println("Light is   OFF")
  }
}

//Light on command-Concrete Command
class LightOnCommand(private val light:Light):Command{
  override fun execute(){
    light.on()
    
  }
  override fun undo(){
    light.off()
  }
}
//  Light off command-Concrete command
class LightOffCommand(private val light:Light):Command{
override fun execute(){
  light.off()
}
override fun undo()
  {
    light.on()
  }
}

//Invoker class:RemoteControl
class RemoteControl{
  private var command: Command?=null

  fun setCommand(command:Command){
    this.command=command
  }

  fun pressButton(){
    command?.execute()
  }

  fun pressUndo(){
    command?.undo()
  }
}

//client Code
fun main(){
  val light=Light()
  val lightOnCommand=LightOnCommand(light)
  val lightOfCommand=LightOffCommand(light)
  val remoteControl=RemoteControl()
  remoteControl.setCommand(lightOnCommand)
  remoteControl.pressButton() 

  remoteControl.setCommand(lightOfCommand)
  remoteControl.pressButton() 


  remoteControl.pressUndo()   
}
