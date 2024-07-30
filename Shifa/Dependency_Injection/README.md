# What is Dependency Injection?
### In the context of Android development, DI helps manage the dependencies of your application by providing them at runtime rather than at compile-time.
### In DI, an object receives its dependencies from an external source rather than creating them internally. This is typically done through constructor injection, method injection, or field injection. By injecting dependencies, 
you decouple the classes from their concrete implementations, making your code more modular, testable, and easier to manage.

#### The term "dependency" in "dependency injection" 
refers to an object or component that another object or component relies on to function properly. 
#### The term "inject" in "dependency injection" 
refers to the process of providing an external dependency to a software component (like a class or object) rather than having the component create the dependency itself. This injection can be done in several ways, such as through a constructor, a method, or directly into fields.

## Advantages
1) Reusability of code
2) Ease of refactoring
3) Ease of testing
4) Decoupling
## Why is Dependency Injection Important?
#### Decoupling:
By using dependency injection, you decouple the creation and management of dependencies from the classes that use them. This allows your code to be more modular and flexible.

#### Flexibility: 
Dependency injection enables you to easily swap or replace dependencies without changing the code that uses them. This is particularly useful for testing, where you might want to inject mock objects.

#### Testability: 
By injecting dependencies, you can provide different implementations or mocks during testing, making it easier to isolate and test individual components.

#### Maintainability: 
It becomes easier to maintain and refactor code since the dependencies are managed externally, making the system more understandable and easier to manage.

## Disadvantages
#### Increased complexity: 
Implementing DI has the potential to introduce additional complexity to your codebase. The need to define dependencies, manage their lifecycles, and configure the injection can make the code harder to understand.
#### Performance overhead: 
DI can introduce some level of performance overhead due to the dynamic resolution of dependencies at runtime. Although modern DI frameworks and toolkits are optimized for efficiency, there can still be a slight impact on the application performance.
#### Increased learning curve: 
Adopting DI requires developers to understand its concepts, principles, and recommended practices. This might come with some initial time overhead and potentially limit development at first as a result of the learning curve. In addition, developers must be wary of pitfalls such as circular dependency, which occurs when two classes depend on each other.
#### Runtime errors: 
Improper or unresolved dependencies can lead to unexpected behavior, application crashes, or errors at runtime. These errors can sometimes be challenging to troubleshoot, particularly in large codebases with complex dependency graphs.
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

# 4. Interface Injection

### How Interface Injection Works
Client Interface: The client class implements an interface that includes a method for injecting the dependency. This method allows an injector to provide the necessary dependencies.

Injector Class: A separate injector class is responsible for creating the dependency and injecting it into the client through the interface method.

Decoupling: Like other forms of dependency injection, interface injection helps decouple the client class from the specific implementations of its dependencies, promoting flexibility and testability.
```kotlin
// Printer interface
interface Printer {
    fun printDocument(document: String)
}

// Concrete implementation of Printer
class ConsolePrinter : Printer {
    override fun printDocument(document: String) {
        println("Printing document: $document")
    }
}

// Document interface for injection
interface Document {
    fun setPrinter(printer: Printer)
    fun printContent()
}

// Document implementation using interface injection
class TextDocument : Document {
    private var printer: Printer? = null

    override fun setPrinter(printer: Printer) {
        this.printer = printer
    }

    override fun printContent() {
        printer?.printDocument("TextDocument content") ?: println("No printer set")
    }
}

// Injector class
class DocumentInjector {
    fun injectPrinter(document: Document, printer: Printer) {
        document.setPrinter(printer)
    }
}

// Usage
fun main() {
    val document = TextDocument()
    val printer = ConsolePrinter()
    val injector = DocumentInjector()

    // Inject the printer into the document
    injector.injectPrinter(document, printer)

    // Print the document content
    document.printContent()
}
```





