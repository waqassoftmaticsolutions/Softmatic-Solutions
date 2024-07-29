//State is a behavioral design pattern that lets an object alter its behavior when its internal state changes. It appears as if the object changed its class.



//State interface
interface TrafficLightState{
  fun handle(trafficLight:TrafficLight)
}
//concrete State
class Green : TrafficLightState{
  override fun handle(trafficLight:TrafficLight){
    println("The traffic light is green,G0!")
    trafficLight.state=Red()
  }
}

//concrete State
class Yellow:TrafficLightState{
  override fun handle(trafficLight:TrafficLight){
    println("The traffic light is Yellow,Ready to Go!")
    trafficLight.state=Green()
  }
}

//concrete State
class Red:TrafficLightState{
  override fun handle(trafficLight:TrafficLight){
    println("The traffic light is Red,Stop!!")
    trafficLight.state=Yellow()
  }
}

//context class
class TrafficLight(var state:TrafficLightState){
  fun change(){
    state.handle(this)
  }
}

fun main(){
  
      val trafficLight = TrafficLight(Green())

      trafficLight.change() 
      trafficLight.change() 
      trafficLight.change() 
      trafficLight.change() 
  

}
