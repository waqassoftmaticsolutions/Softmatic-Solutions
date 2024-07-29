// Define the Subscriber interface with a method to receive notifications
interface Subscriber {
    fun update(message: String)
}

// Implement a concrete Subscriber class
class ConcreteSubscriber(private val name: String) : Subscriber {
    override fun update(message: String) {
        println("$name received: $message")
    }
}

// Define the Publisher class
class Publisher {
    private val subscribers = mutableListOf<Subscriber>()

    // Method to add a subscriber
    fun subscribe(subscriber: Subscriber) {
        subscribers.add(subscriber)
    }

    // Method to remove a subscriber
    fun unsubscribe(subscriber: Subscriber) {
        subscribers.remove(subscriber)
    }

    // Method to notify all subscribers about an event
    fun notifySubscribers(message: String) {
        for (subscriber in subscribers) {
            subscriber.update(message)
        }
    }

    // Simulate an event that triggers notifications
    fun eventHappened() {
        notifySubscribers("An important event has occurred!")
    }
}

// Example usage
fun main() {
    val publisher = Publisher()

    // Create subscribers
    val subscriber1 = ConcreteSubscriber("Subscriber 1")
    val subscriber2 = ConcreteSubscriber("Subscriber 2")
    val subscriber3 = ConcreteSubscriber("Subscriber 3")

    // Subscribe to the publisher
    publisher.subscribe(subscriber1)
    publisher.subscribe(subscriber2)
    publisher.subscribe(subscriber3)

    // Trigger an event
    publisher.eventHappened()

    // Unsubscribe one subscriber
    publisher.unsubscribe(subscriber2)

    // Trigger another event
    publisher.eventHappened()
}
