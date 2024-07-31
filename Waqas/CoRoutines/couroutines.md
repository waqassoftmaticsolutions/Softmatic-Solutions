# Kotlin Coroutines
1) It is concurrency design pattern
2) You can use on android to simplify code that executes asynchrounously
3) Coroutines are a feature of asynchronous programming allowing you to write a code that can perform tasks concurrently in a sequential manner
4) Part of Kotlin's cocurrency framework
5) Corourtines help to manage long-running tasks that might otherwise block main thread and cause your app to become unresponsive
6) Using couroutines you can increased productivity

## First Couroutine
```Kotlin
fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}
```
**Launch** is a coroutine builder. It launches a new coroutine concurrently with the rest of the code, which continues to work independently. That's why Hello has been printed first.
**Delay** is a special suspending function. It suspends the coroutine for a specific time. Suspending a coroutine does not block the underlying thread, but allows other coroutines to run and use the underlying thread for their code.
**runBlocking** is also a coroutine builder that bridges the non-coroutine world of a regular fun main() and the code with coroutines inside of runBlocking { ... } curly braces. This is highlighted in an IDE by this: CoroutineScope hint right after the runBlocking opening curly brace.
If you remove or forget **runBlocking** in this code, you'll get an error on the launch call, since launch is declared only on the CoroutineScope.

## Extract function refactoring
Let's extract the block of code inside launch { ... } into a separate function.
```Kotlin
fun main() = runBlocking { // this: CoroutineScope
    launch { doWorld() }
    println("Hello")
}
// this is your first suspending function
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}
```

## Scope builder
```Kotlin
import kotlinx.coroutines.*
fun main() {
    println("WAQAS")
    runBlocking {
        doWorld()
    }
    println("Umar")
    runBlocking {
        doWorld1()
    }
    println("Ehtisham")
}
suspend fun doWorld1() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2000L)
        println("Hello 1")
    }
    launch {
        delay(3000L)
        println("Hello 2")
    }
    println("Hello 11")
}

suspend fun doWorld() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(3000L)
        println("World 1")
    }
    println("Hello")
}
```
#### Output
```text
WAQAS
Hello
World 2
World 1
Umar
Hello 11
Hello 1
Hello 2
Ehtisham
```

## An explicit job﻿
A launch coroutine builder returns a Job object that is a handle to the launched coroutine and can be used to explicitly wait for its completion. For example, you can wait for completion of the child coroutine and then print remaining code.
### Example 1
```kotlin
import kotlinx.coroutines.*

fun main() {
    println("WAQAS")
    runBlocking {
        doWorld1()
    }
    println("Ehtisham")
}
suspend fun doWorld1() { // this: CoroutineScope
    coroutineScope{
        var job = launch {
            delay(5000L)
            println("Hello 1")
        }
        job.join()
        launch {
            delay(3000L)
            println("Hello 2")
        }
    }
    println("Hello 11")
}
```
#### Output
```Text
WAQAS
Hello 1
Hello 2
Hello 11
Ehtisham
```
### Example 2
```Kotlin
import kotlinx.coroutines.*

fun main() {
    println("WAQAS")
    runBlocking {
        doWorld1()
    }
    println("Ehtisham")
}
suspend fun doWorld1() { // this: CoroutineScope
    coroutineScope{
        var job = launch {
            delay(5000L)
            println("Hello 1")
        }
        launch {
            delay(3000L)
            println("Hello 2")
        }
        println("Hello 11")
        job.join()
        println("Hello 22")
    }   
}
```
#### Output
```Text
WAQAS
Hello 11
Hello 2
Hello 1
Hello 22
Ehtisham
```

## Coroutines are light-weight
Coroutines ar eless weight as compared to JVM threads because they don't have their own stack. Coroutines are created and destroyed more efficiently, and can handle many concurrent tasks with lower overhead.
For example, the following code launches 50,000 distinct coroutines that each waits 5 seconds and then prints a period ('.') while consuming very little memory:
```Kotlin
import kotlinx.coroutines.*
fun main() = runBlocking {
    repeat(5) { // launch a lot of coroutines
        launch {
            delay(1000L)
            print("A")
        }
        println("B")
    }
}
```
#### Output
```Text
B
B
B
B
B
AAAAA
```

### Example 2
```Kotlin
import kotlinx.coroutines.*
fun main() = runBlocking {
    repeat(5) { // Launch coroutines
        val job = launch {
            delay(1000L)
            print("A")
        }
        job.join()  // Wait for the coroutine to finish
        println("B")
    }
}
```
#### Output
```Text
AB
AB
AB
AB
AB
```

### Example 3 using Threads
```Kotlin
fun main() {
    repeat(5) { // launch a lot of threads
        val th = Thread {
            Thread.sleep(1000L)
            print("A")
        }
        th.start() // Start the thread
        th.join()  // Wait for the thread to finish
        println("B")
    }
}
```
#### Output
```Text
AB
AB
AB
AB
AB
```
