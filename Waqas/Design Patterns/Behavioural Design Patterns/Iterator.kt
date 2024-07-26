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

//Iterator --> It is a behavioral design pattern that lets you traverse elements of a collection without exposing its underlying representation (list, stack, tree, etc.).

interface Iterator<T>{
  fun hasNext():Boolean
  fun next():Any
}

interface Container<T>{
  fun getIterator():Iterator<T>
}

class NameRepository : Container<String> {
    private val names = arrayOf("Waqas","Ehtisham","Umar","Qadeer","Usman")
    override fun getIterator(): Iterator<String> {
        return NameIterator()
    }
    private inner class NameIterator : Iterator<String> {
        private var index = 0

        override fun hasNext(): Boolean {
            return index < names.size
        }

        override fun next(): String {
            if (this.hasNext()) {
                return names[index++]
            }
            throw NoSuchElementException("No more elements")
        }
    }
}

fun main() {
    val nameRepository = NameRepository()

    val iterator = nameRepository.getIterator()
    while (iterator.hasNext()) {
        val name = iterator.next()
        println("Name: $name")
    }
}
