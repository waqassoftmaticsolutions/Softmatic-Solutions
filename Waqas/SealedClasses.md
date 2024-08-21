# Sealed CLasses
Sealed classes are a special kind of class in Kotlin that allow you to represent a restricted hierarchy of classes. When you declare a sealed class, you define a set of subclasses that can inherit from it. These subclasses are known at compile-time, which means that no other classes can inherit from the sealed class unless they are nested within the sealed class or declared within the same file.In simpler terms, a sealed class is like an enum with more flexibility. While enums can only represent a fixed set of instances, sealed classes can represent a fixed set of subclasses, each of which can hold different data.
It is used to define a closed set of subclasses. Sealed classes allow us to create instances with different types, unlike Enums which restrict us to use the same type for all enum constants. The following isn’t possible in Enum classes.
## Purpose
1) Sealed classes are used to represent a type hierarchy where all possible types are known at compile time.
2) When you use a sealed class in a when expression, Kotlin will ensure that you handle all possible cases. If you miss any subclass, the compiler will warn you, which leads to safer code.

## Characterisstics
1) **Restrictive Inheritance:** All subclasses of a sealed class are known at compile-time, providing a controlled inheritance structure.
2) **Exhaustive when Expressions:** When using when expressions with sealed classes, Kotlin ensures that all possible subclasses are handled, leading to safer and more maintainable code.
3) **Improved Type Safety:** By restricting inheritance, you prevent unforeseen implementations that could violate the intended design.
4) **Clarity and Readability:** Sealed classes provide a clear way to represent and handle different states or events in your application. By defining subclasses within a sealed class, the code becomes more readable and understandable.

## Example
```kotlin
sealed class Shape {
    class Circle(val radius: Double) : Shape()
    class Rectangle(val width: Double, val height: Double) : Shape()
    object NotAShape : Shape()
}
fun describeShape(shape: Shape): String {
    return when (shape) {
        is Shape.Circle -> "A circle with radius ${shape.radius}"
        is Shape.Rectangle -> "A rectangle with width ${shape.width} and height ${shape.height}"
        Shape.NotAShape -> "Not a shape"
    }
}
```
## Usecases
1) Representing Success and Failure States: Common in network operations or database queries, where you might have a Result type representing success, error, or loading states.
2) Type-safe Event Handling: Ensuring all possible events are handled in user interfaces or reactive systems.

## Benefits
1) **Type Safety:** Ensures that all possible subclasses are handled, reducing the risk of runtime errors.
2) **Better Readability:** Makes the code more readable and maintainable by grouping related types.
3) **Compile-Time Checks:** Kotlin compiler provides checks for exhaustive when expressions, reducing the chances of missing any subclass.
4) **Flexibility Over Enums:** Sealed classes can have multiple instances with different states and data, providing more flexibility than enums.
