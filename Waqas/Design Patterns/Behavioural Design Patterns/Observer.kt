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

// Observer --> Observer pattern is used when there is one-to-many relationship between objects such as if one object is modified, its depenedent objects are to be notified automatically. Subject is directly connected with the client.

// Subject
interface TemperatureSensor {
    fun addObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}

// ConcreteSubject
class ConcreteTemperatureSensor : TemperatureSensor {
    private val observers = mutableListOf<Observer>()
    private var temperature: Int = 0
    fun setTemperature(temperature: Int) {
        this.temperature = temperature
        notifyObservers()
    }
    fun getTemperature(): Int = temperature

    override fun addObserver(observer: Observer) {
        observers.add(observer)
    }
    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }
    override fun notifyObservers() {
        observers.forEach { it.update() }
    }
}

// Observer
interface Observer {
    fun update()
}

// ConcreteObserver
class TemperatureDisplay(private val sensor: ConcreteTemperatureSensor) : Observer {
    init {
        sensor.addObserver(this)
    }
    override fun update() {
        println("Current temperature: ${sensor.getTemperature()}°C")
    }
}

fun main() {
    val sensor = ConcreteTemperatureSensor()
    val display = TemperatureDisplay(sensor)

    println("Setting temperature to 20°C")
    sensor.setTemperature(20)

    println("Setting temperature to 25°C")
    sensor.setTemperature(25)

    println("Removing the display observer")
    sensor.removeObserver(display)

    println("Setting temperature to 30°C")
    sensor.setTemperature(30)
}
