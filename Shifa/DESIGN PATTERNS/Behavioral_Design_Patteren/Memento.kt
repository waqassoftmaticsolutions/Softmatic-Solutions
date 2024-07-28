//The Memento Design Pattern is a behavioral design pattern that allows an object to save and restore its previous state without exposing its implementation details. 
//This pattern is commonly used to implement undo/redo functionality.

// Memento class
class Memento(val state: String)

// Originator class
class TextEditor {
    private var text: String = ""

    fun type(words: String) {
        text += words
    }

    fun getText(): String {
        return text
    }

    fun save(): Memento {
        return Memento(text)
    }

    fun restore(memento: Memento) {
        text = memento.state
    }
}

// Caretaker class
class Caretaker {
    private val mementos = mutableListOf<Memento>()

    fun saveMemento(memento: Memento) {
        mementos.add(memento)
    }

    fun getMemento(index: Int): Memento {
        return mementos[index]
    }
}

// Main function
fun main() {
    val textEditor = TextEditor()
    val caretaker = Caretaker()

    textEditor.type("Hello, ")
    caretaker.saveMemento(textEditor.save())

    textEditor.type("World!")
    caretaker.saveMemento(textEditor.save())

    println("Current Text: ${textEditor.getText()}") 

    textEditor.restore(caretaker.getMemento(0))
    println("Restored Text: ${textEditor.getText()}") 
}
