// Structural design patterns in software engineering focus on how classes and objects are composed to form larger structures while maintaining flexibility and efficiency. These patterns typically deal with relationships between objects, simplifying the design and making the system more manageable.
// 1) Adaptor
// 2) Bridge
// 3) Composite
// 4) Decorator
// 5) Facade
// 6) Flyweight
// 7) Proxy

// Facade --> Facade pattern hides the complexities of the system and provides an interface to the client using which the client can access the system. This type of design pattern comes under structural pattern as this pattern adds an interface to existing system to hide its complexities.

//Subsystem Classes
class Lights{
  fun onLights(){
    println("Lights are ON")
  }
  fun offLights(){
    println("Lights are OFF")
  }
}
class Thermostat{
  fun setTemperature(temperature: Double){
    println("Setting termperature at : $temperature")
  }
}
class Camera{
  fun setCameraOn(){
    println("Security Cameras are ON")
  }
  fun setCameraOff(){
    println("Security Cameras are OFF")
  }
}
class Doors{
  fun openDoors(){
    println("Opening the Door")
  }
  fun closeDoors(){
    println("Closing the Doors")
  }
}

//Facade
class House{
  private var lights: Lights = Lights()
  private var thermostat:Thermostat = Thermostat()
  private var camera: Camera = Camera()
  private var doors: Doors = Doors()

  fun enteringHome(){
    println("You are entering in your house")
    doors.openDoors()
    lights.onLights()
    thermostat.setTemperature(16.0)
    camera.setCameraOff()
  }
  fun exitingHome(){
    println("You are exiting your house")
    doors.closeDoors()
    lights.offLights()
    thermostat.setTemperature(30.0)
    camera.setCameraOn()
  }
}
fun main(){
  var house = House()
  house.enteringHome()
  house.exitingHome()
}
