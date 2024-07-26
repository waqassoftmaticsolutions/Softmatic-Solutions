// Behavioral Design Pattern --> Behavioral design patterns are concerned with algorithms and the assignment of responsibilities between objects.
// 1) Chain of Responsibility
// 2) Command
// 3) Iterarator
// 4) Mediator
// 5) Memento
// 6) Obserever
// 7) State
// 8) Strategy
// 9) Template Method
// 10) Visitor

//Mediator --> It is a behavioral design pattern that lets you reduce chaotic dependencies between objects. The pattern restricts direct communications between the objects and forces them to collaborate only via a mediator object.

// Mediator Interface
interface HomeMediator {
    fun notifyDevices(event: String)
}

// Concrete Mediator
class ConcreteHomeMediator : HomeMediator {
    private val devices = mutableListOf<Device>()
    fun registerDevice(device: Device) {
        devices.add(device)
    }
    override fun notifyDevices(event: String) {
        devices.forEach { it.receive(event) }
    }
}

// Colleague
abstract class Device(val name: String, private val mediator: HomeMediator) {
    fun trigger(event: String) {
        mediator.notifyDevices(event)
    }
    abstract fun receive(event: String)
}

// Concrete Colleague
class Light(name: String, mediator: HomeMediator) : Device(name, mediator) {
    override fun receive(event: String) {
        println("$name light is turned $event")
    }
}

class Thermostat(name: String, mediator: HomeMediator) : Device(name, mediator) {
    override fun receive(event: String) {
        println("$name thermostat set to $event")
    }
}

fun main() {
    val homeMediator = ConcreteHomeMediator()
    val livingRoomLight = Light("Living Room", homeMediator)
    val thermostat = Thermostat("Main", homeMediator)
    homeMediator.registerDevice(livingRoomLight)
    homeMediator.registerDevice(thermostat)
    livingRoomLight.trigger("on")
    thermostat.trigger("22°C")
}
