# What is Dependency Injection?
### In the context of Android development, DI helps manage the dependencies of your application by providing them at runtime rather than at compile-time.
### In DI, an object receives its dependencies from an external source rather than creating them internally. This is typically done through constructor injection, method injection, or field injection. By injecting dependencies, 
you decouple the classes from their concrete implementations, making your code more modular, testable, and easier to manage.

## Advantages
1) Reusability of code
2) Ease of refactoring
3) Ease of testing
4) Decoupling

# 1. Constructor Injection
What It Is: Constructor injection involves providing dependencies through the constructor of a class. This ensures that the dependencies are immutable and must be provided when the object is created.

## Violation Example:
If you modify the Car class to create its own Engine instance, it violates constructor injection:
```kotlin
class Car {
    private val engine: Engine = Engine()  // Violation: Engine is created within Car

    fun drive() {
        engine.start()
        println("Car is driving")
    }
}
```
## CORRECTED CODE
```kotlin
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
```

# 2. Method Injection
 What It Is: Method injection involves providing dependencies via setter methods or other methods after the object is created. This allows dependencies to be set or changed after the object’s creation.
## Violation of DI
```kotlin
class Car {
    private lateinit var engine: Engine

    fun drive() {
        if (::engine.isInitialized) {
            engine.start()
            println("Car is driving")
        } else {
            println("Engine is not set")
        }
    }

    // Method to set engine should ideally be called before drive() is called
}
```

## CORRECTED CODE
```kotlin
// Dependency
class Engine {
    fun start() {
        println("Engine started")
    }
}

// Car class with method injection
class Car {
    private lateinit var engine: Engine

    fun setEngine(engine: Engine) {
        this.engine = engine
    }

    fun drive() {
        if (::engine.isInitialized) {
            engine.start()
            println("Car is driving")
        } else {
            println("Engine is not set")
        }
    }
}

// Usage
fun main() {
    val engine = Engine()
    val car = Car()
    car.setEngine(engine)
    car.drive()
}
```
# 3. Field Injection
What It Is: Field injection involves directly injecting dependencies into the fields of a class. This is less common in modern practices but can be seen in some DI frameworks.
```kotlin
// Dependency
class Engine {
    fun start() {
        println("Engine started")
    }
}

// Car class with field injection (using a DI framework like Dagger or Hilt)
class Car {
    @Inject lateinit var engine: Engine  // Field injection

    fun drive() {
        engine.start()
        println("Car is driving")
    }
}

// Usage
// The DI framework will handle the injection
```



