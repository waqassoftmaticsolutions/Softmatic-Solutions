# Extension Functions
Extension functions in Kotlin allow you to add new functionality to existing classes without modifying their source code. This is achieved through a feature known as extensions. When a function is added to an existing class it is known as Extension Function. Essentially, you can "extend" a class with new methods as if they were part of the original class. This is done by defining a function outside the class but with the class as its receiver.
## Note
1) We can add properties or methods to our existing class
2) By this method we can't access class private member variables or functions.

## Purpose
The main purpose of extension functions is to make code more readable and maintainable by allowing you to add functionality to classes without the need to inherit from them or use cumbersome utility methods. They allow you to work with objects in a more idiomatic Kotlin way, leading to cleaner and more concise code.
**Scenario:** In a large codebase, it’s common to work with classes that serve multiple purposes. Adding every utility function directly to these classes can make them large and difficult to manage.
**Solution:** Extension functions allow you to keep the primary class definition simple and concise, while still providing the flexibility to add and use additional functions where needed.
## Important note
One important point to note about the extension functions is that they are resolved statically i.e which extension function is executed depends totally on the type of the expression on which it is invoked, rather than on the type resolved on the final execution of the expression at runtime.
```kotlin
// Open class created to be inherited
open class A(val a:Int, val b:Int){
}

// Class B inherits A
class B():A(5, 5){}

fun main(){
	
	// Extension function operate defined for A
	fun A.operate():Int{
		return a+b
	}

	// Extension function operate defined for B
	fun B.operate():Int{
		return a*b;
	}

	// Function to display static dispatch
	fun display(a: A){
		print(a.operate())
	}

	// Calling display function 
	display(B())
}
```
Extension functions can also be defined with the class type that is nullable. In this case, when the check for null is added inside the extension function and the appropriate value is returned.
```kotlin
// A sample class to display name name
class AB(val name: String){
	override fun toString(): String {
		return "Name is $name"
	}
}

fun main(){
	// An extension function as a nullable receiver
	fun AB?.output(){
		if(this == null){
			println("Null")
		}else{
			println(this.toString())
		}
	}

	val x = AB("Charchit")
	
	// Extension function called using an instance
	x.output()
	// Extension function called on null
	null.output()
}
```
## Advantages
1) Extension functions let you extend classes without modifying their code or needing to subclass them. This is especially useful when dealing with third-party libraries or standard classes.
2) By adding methods that are directly related to the class's purpose, you can make your code easier to read and understand.
3) They provide a flexible way to add functionality to classes, especially when dealing with libraries where subclassing might not be feasible or desirable.

## Disadvantages
1) Overuse of extension functions can lead to confusion, especially for developers who are not familiar with the codebase, as they might not be immediately obvious as extensions
2) If multiple extension functions with the same name are available in the same scope, it can lead to ambiguity and errors.
3) 
## Example
```kotlin
fun String.wordCount(): Int {
    return this.trim().split("\\s+".toRegex()).size
}
fun main(){
  val text = "Hello, how are you?"
  val count = text.wordCount()
  println("Word count: $count")  // Output: Word count: 4
}
```
In this example:
1) String is the receiver type, meaning this function will be added to the String class.
2) wordCount is the name of the extension function.
3) this refers to the instance of the String on which the function is called.
