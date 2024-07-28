// Subject Interface
interface Subject {
    fun registerObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}

// Observer Interface
interface Observer {
    fun update(temperature: Float)
}

// Concrete Subject
class WeatherStation : Subject {
    private val observers = mutableListOf<Observer>()
    private var temperature: Float = 0.0f

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        for (observer in observers) {
            observer.update(temperature)
        }
    }

    fun setTemperature(temp: Float) {
        println("WeatherStation: New temperature is $temp")
        temperature = temp
        notifyObservers()
    }
}

// Concrete Observer
class Display : Observer {
    private var temperature: Float = 0.0f

    override fun update(temperature: Float) {
        this.temperature = temperature
        display()
    }

    fun display() {
        println("Display: Current temperature is $temperature")
    }
}

// Main Function
fun main() {
    val weatherStation = WeatherStation()
    val display1 = Display()
    val display2 = Display()

    weatherStation.registerObserver(display1)
    weatherStation.registerObserver(display2)

    weatherStation.setTemperature(25.0f)
    weatherStation.setTemperature(30.0f)

    weatherStation.removeObserver(display1)
    weatherStation.setTemperature(28.0f)
}
