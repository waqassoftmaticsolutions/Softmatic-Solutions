#What is Dependency Injection?
##In the context of Android development, DI helps manage the dependencies of your application by providing them at runtime rather than at compile-time.
##In DI, an object receives its dependencies from an external source rather than creating them internally. This is typically done through constructor injection, method injection, or field injection. By injecting dependencies, 
you decouple the classes from their concrete implementations, making your code more modular, testable, and easier to manage.

#Advantages
1) Reusability of code
2) Ease of refactoring
3) Ease of testing
4) Decoupling

#1. Constructor Injection
###What It Is: Constructor injection involves providing dependencies through the constructor of a class. This ensures that the dependencies are immutable and must be provided when the object is created.

##Violation Example:
If you modify the Car class to create its own Engine instance, it violates constructor injection:
class Car {
    private val engine: Engine = Engine()  // Violation: Engine is created within Car

    fun drive() {
        engine.start()
        println("Car is driving")
    }
}
##CORRECTED CODE
// Dependency
class Engine {
    fun start() {
        println("Engine started")
    }
}

// Car class with constructor injection
class Car(private val engine: Engine) {
    fun drive() {
        engine.start()
        println("Car is driving")
    }
}

// Usage
fun main() {
    val engine = Engine()
    val car = Car(engine)
    car.drive()
}


