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

// Memento --> It is a behavioral design pattern that lets you capture and externalize an object's internal state, allowing you to restore it to that state later.

// Originator --> It is the object whose internal state we want to save.
class Originator {
    private var state: String = ""

    fun setState(state: String) {
        this.state = state
    }

    fun getState(): String {
        return state
    }
    // Creates a memento object that stores the current state
    fun createMemento(): Memento {
        return Memento(state)
    }
    // Restores the state from a memento object
    fun restoreMemento(memento: Memento) {
        this.state = memento.getState()
    }
}

// Memento --> It is the object that stores the internal state of the Originator.
class Memento(private var state: String) {
    fun getState(): String {
        return state
    }
}

// Caretaker --> It is the object that is responsible for saving and restoring the memento objects.
class Caretaker {
    private val mementos = ArrayList<Memento>()

    fun addMemento(memento: Memento) {
        mementos.add(memento)
    }

    fun getMemento(index: Int): Memento {
        return mementos[index]
    }
}

fun main() {
    val originator = Originator()
    val caretaker = Caretaker()

    // Save the initial state
    originator.setState("State 1")
    caretaker.addMemento(originator.createMemento())

    // Change the state
    originator.setState("State 2")
    caretaker.addMemento(originator.createMemento())

    // Change the state again
    originator.setState("State 3")
    caretaker.addMemento(originator.createMemento())

    // Restore the state to "State 1"
    originator.restoreMemento(caretaker.getMemento(0))
    println("Current State: ${originator.getState()}")
    // Restore the state to "State 2"
    originator.restoreMemento(caretaker.getMemento(1))
    println("Current State: ${originator.getState()}")
    
}
