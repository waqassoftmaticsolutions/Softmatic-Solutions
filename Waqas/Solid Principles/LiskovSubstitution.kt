interface Vehicle {
  fun moveForward()
  fun moveBackward()
}

abstract class vehicleWithEngine : Vehicle {
  private var isEngineWorking = false
  open fun startEngine() {
    isEngineWorking = true
  }
  open fun stopEngine() {
    isEngineWorking = false
  }
}

class Car : vehicleWithEngine() {
  override fun startEngine() {
    super.startEngine()
    println("Starting Car engine")
  }
  override fun stopEngine() {
    super.stopEngine()
    println("Stoping Car Engine")
  }
  override fun moveForward() {
    println("Moving Car Forward")
  }
  override fun moveBackward() {
    println("Moving Car Backward")
  }
}

class Bicycle : Vehicle {
  override fun moveForward() {
    println("Moving Bicycle Forward")
  }
  override fun moveBackward() {
    println("Moving Bicycle Backward")
  }
}

fun main() {
  var bicycle = Bicycle()
  var car = Car()
  bicycle.moveForward()
  bicycle.moveBackward()
  car.startEngine()
  car.stopEngine()
  car.moveForward()
  car.moveBackward()
}
