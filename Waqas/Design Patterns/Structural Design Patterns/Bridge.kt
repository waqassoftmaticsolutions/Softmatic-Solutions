// Structural design patterns in software engineering focus on how classes and objects are composed to form larger structures while maintaining flexibility and efficiency. These patterns typically deal with relationships between objects, simplifying the design and making the system more manageable.
// 1) Adaptor
// 2) Bridge
// 3) Composite
// 4) Decorator
// 5) Facade
// 6) Flyweight
// 7) Proxy

// Bridge --> Bridge is a structural design pattern that lets you split a large class or a set of closely related classes into two separate hierarchies—abstraction and implementation—which can be developed independently of each other.

// Abstraction: RemoteControl
abstract class RemoteControl(protected val device: Device) {
    abstract fun turnOn()
    abstract fun turnOff()
    abstract fun setVolume(volume: Int)
}

// Implementor Interface: Device
interface Device {
    fun turnOn()
    fun turnOff()
    fun setVolume(volume: Int)
}

// ConcreteImplementor 1: TV
class TV : Device {
    private var volume: Int = 0

    override fun turnOn() {
        println("TV is now ON.")
    }

    override fun turnOff() {
        println("TV is now OFF.")
    }

    override fun setVolume(volume: Int) {
        this.volume = volume
        println("TV volume set to $volume.")
    }
}

// ConcreteImplementor 2: Radio
class Radio : Device {
    private var volume: Int = 0

    override fun turnOn() {
        println("Radio is now ON.")
    }

    override fun turnOff() {
        println("Radio is now OFF.")
    }

    override fun setVolume(volume: Int) {
        this.volume = volume
        println("Radio volume set to $volume.")
    }
}

// RefinedAbstraction 1: BasicRemote
class BasicRemote(device: Device) : RemoteControl(device) {
    override fun turnOn() {
        device.turnOn()
    }

    override fun turnOff() {
        device.turnOff()
    }

    override fun setVolume(volume: Int) {
        device.setVolume(volume)
    }
}

fun main() {
    val tv: Device = TV()
    val radio: Device = Radio()
    val basicRemoteForTV = BasicRemote(tv)
    val basicRemoteForRadio = BasicRemote(radio)
    basicRemoteForTV.turnOn()
    basicRemoteForTV.setVolume(10)
    basicRemoteForTV.turnOff()
    basicRemoteForRadio.turnOn()
    basicRemoteForRadio.setVolume(5)
    basicRemoteForRadio.turnOff()
}
