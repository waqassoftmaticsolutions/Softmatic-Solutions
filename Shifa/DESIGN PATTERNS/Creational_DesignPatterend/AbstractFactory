//In Abstract Factory pattern an interface is responsible for creating a factory of related objects without explicitly specifying their classes. 
//Each generated factory can give the objects as per the Factory pattern.

//interface
public interface ElectronicDevices{
  fun create()
}

//concrete classes implementing interface
class SamsungMobile():ElectronicDevices{
  override fun create() {
  println("Samsung mobile is created")
  }
}

class OnePlusMobile():ElectronicDevices{
   override fun create() {
    println("OnePlus mobile is created")
    }
}

class SamsungTablet():ElectronicDevices{
   override fun create() {
    println("Samsung Tablet is created")
    }
}

class LenovoTablet():ElectronicDevices{
  override fun create(){
    println("Lenovo Tablet is created")
  }
}

//abstract factory
abstract class ElectronicDevicesFactory{
  abstract fun createDevice(deviceType:String):ElectronicDevices
}

//factory classes
class MobileFactory:ElectronicDevicesFactory(){
  override fun createDevice(deviceType:String):ElectronicDevices{
    if(deviceType.equals("SAMSUNG",ignoreCase=true)){
      return SamsungMobile()
    }
    else if(deviceType.equals("ONEPLUS",ignoreCase=true)){
        return OnePlusMobile()
    }
    else{
      return SamsungMobile()
    }
  }
}

class TabletFactory:ElectronicDevicesFactory(){
  override fun createDevice(deviceType:String):ElectronicDevices{
    if(deviceType.equals("SAMSUNG",ignoreCase=true)){
      return SamsungTablet()
    }
    else if(deviceType.equals("Lenovo",ignoreCase=true)){
      return LenovoTablet()
    }
    else{
      return LenovoTablet()
    }
  }
}
//Create a Factory generator/producer class to get factories by passing an information such as electronic devicetype

class FactoryProducer{
  companion object {
    fun getFactory(device:String):ElectronicDevicesFactory{
      return when(device){
        "TABLET" -> TabletFactory()
        "MOBILE" -> MobileFactory()
        else -> throw IllegalArgumentException("Not available this type of mobile at the moment")
      }
    }
  }

}

//Use the FactoryProducer to get AbstractFactory in order to get factories of concrete classes by passing an information 

fun main(){

  var electroicdevicefactory:ElectronicDevicesFactory=FactoryProducer.getFactory("MOBILE")
  var electronicdevice:ElectronicDevices=electroicdevicefactory.createDevice("SAMSUNG")
  electronicdevice.create();
println("")
  println("Now we make tablet")
  println("")
   var electroicdevicefactory2:ElectronicDevicesFactory=FactoryProducer.getFactory("TABLET")
    var electronicdevice2:ElectronicDevices=electroicdevicefactory2.createDevice("lenovo")
    electronicdevice2.create();
  
}
  
