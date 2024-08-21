# Enum vs Sealed Classes
Enum only suppoer constant value,
it can not hold any kind of dynamic object and variable whose values change in future

## To overcome this we have Sealed Classes (means restriction) in kotlin
1) You can add restriction to sealed classes
2) Sealed classes are actually abstract class,we can not make object of sealed class
3) its subclasses are define within it or same file but not outside file ,mostly we write it within class
4) A sealed class is a class that is marked with the sealed keyword in Kotlin.
5) It is used to define a closed set of subclasses.
6) It allows you to define a restricted class hierarchy in which subclasses are predefined and finite. T
7) he subclasses of a sealed class are defined within the sealed class itself, and each subclass must be declared as inner or data or class, with no other modifiers allowed.
#### Type Safety:
8 ) When using when expressions with sealed classes, you don't need to include an else branch because the compiler knows all possible subclasses. This ensures exhaustive checking and helps catch errors at compile-time.

## Syntax of a Sealed Class:
The syntax of a sealed class in Kotlin is as follows:
```kotlin
sealed class SealedClassName {
    // Subclasses
    class SubclassName1 : SealedClassName()
    class SubclassName2 : SealedClassName()
    // ...
}
```
### Use Cases of Sealed Classes:
Sealed classes are useful in many situations where you have a fixed set of possible classes that need to be represented. Here are some common use cases for sealed classes:

#### Representing the Result of an Operation:
## One common use case for sealed classes is to represent the result of an operation. For example, We might define a sealed class called Result with two subclasses: Success and Error.
```kotlin
sealed class Result {
    data class Success(val data: String) : Result()
    data class Error(val message: String) : Result()
}
With this definition, We can use a when expression to handle all possible cases of Result, like this:

fun handleResult(result: Result) {
    when(result) {
        is Result.Success -> println(result.data)
        is Result.Error -> println(result.message)
    }
}
```
## State Machine:
Another common use case for sealed classes is to represent the states of a state machine. For example, We might define a sealed class called State with subclasses representing different states of a game.
```kotlin
sealed class State {
    object Initial : State()
    object Running : State()
    object Paused : State()
    object Finished : State()
}

With this definition, we can use a when expression to handle all possible cases of State, like this:

fun handleState(state: State) {
    when(state) {
        is State.Initial -> println("The game is starting...")
        is State.Running -> println("The game is running...")
        is State.Paused -> println("The game is paused...")
        is State.Finished -> println("The game is finished!")
    }
}
```

## Handling UI States:
A common use case for sealed classes is handling different UI states in Android applications. For example, you might define a sealed class called ViewState subclasses representing different UI states of a screen.

sealed class ViewState {
    object Loading : ViewState()
    data class Success(val data: List<String>) : ViewState()
    data class Error(val message: String) : ViewState()
}
With this definition, We can use an when expression to handle all possible cases of ViewState, like this:

fun handleViewState(viewState: ViewState) {
    when(viewState) {
      is ViewState.Loading -> showLoadingIndicator()
      is ViewState.Success -> showData(viewState.data)
      is ViewState.Error -> showError(viewState.message)
  }
}
Here are some more examples of how sealed classes can be used to implement design patterns:

## State pattern:
The state pattern is used to represent the state of an object and the behavior that should be executed based on that state. Sealed classes can be used to define the different states of the object, and each state can have its own behavior.
```kotlin
sealed class State {
    abstract fun handle()
}

object IdleState : State() {
    override fun handle() {
        // Do nothing
    }
}

object ActiveState : State() {
    override fun handle() {
        // Do something when in active state
    }
}

object InactiveState : State() {
    override fun handle() {
        // Do something when in inactive state
    }
}
With this definition, We can use a when expression to handle all possible states of the object and execute the appropriate behavior, like this:

fun handleState(state: State) {
    when(state) {
        is IdleState -> state.handle()
        is ActiveState -> state.handle()
        is InactiveState -> state.handle()
    }
}
```
# Open Classes
In Kotlin, the `open` keyword is used to allow a class to be subclassed. By default, all classes in Kotlin are `final`, meaning they cannot be extended. The `open` keyword changes this default behavior. Here’s why you might use `open` classes:

1. **Extensibility**: When you want a class to be extended by other classes, you need to mark it as `open`. This is useful for creating a base class that other classes can build upon.

2. **Polymorphism**: To take advantage of polymorphism, where you can use a base class reference to hold instances of derived classes, you need the base class to be `open`. This allows methods to be overridden and properties to be changed in subclasses.

3. **Design Patterns**: Many design patterns, such as the Template Method pattern or Strategy pattern, rely on creating classes that can be extended. Using `open` classes allows you to define a flexible and extensible class hierarchy.

4. **Mocking and Testing**: In unit testing, you might need to mock or stub classes. Having `open` classes makes it possible to create mock implementations or override methods to provide test-specific behavior.

5. **Framework Integration**: Some frameworks or libraries might require extending classes to provide custom behavior or integrate with the system. Marking classes as `open` allows you to extend them as needed.

Here’s an example of how `open` classes can be used:

```kotlin
open class Animal {
    open fun makeSound() {
        println("Some generic animal sound")
    }
}

class Dog : Animal() {
    override fun makeSound() {
        println("Bark")
    }
}

fun main() {
    val myDog = Dog()
    myDog.makeSound()  // Outputs: Bark
}
```

In this example, `Animal` is an `open` class with an `open` method `makeSound()`. `Dog` is a subclass of `Animal` and overrides the `makeSound()` method to provide a specific implementation.
