# Coroutine
A coroutine is an instance of a suspendable computation. It is conceptually similar to a thread, in the sense that it takes a block of code to run that works concurrently with the rest of the code. However, a coroutine is not bound to any particular thread. It may suspend its execution in one thread and resume in another one.

```kotlin
fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}
```
**launch** is a coroutine builder. It launches a new coroutine concurrently with the rest of the code, which continues to work independently. That's why Hello has been printed first.

**delay** is a special suspending function. It suspends the coroutine for a specific time. Suspending a coroutine does not block the underlying thread, but allows other coroutines to run and use the underlying thread for their code.

**runBlocking** is also a coroutine builder that bridges the non-coroutine world of a regular fun main() and the code with coroutines inside of runBlocking { ... } curly braces. This is highlighted in an IDE by this: CoroutineScope hint right after the runBlocking opening curly brace.

If you remove or forget runBlocking in this code, you'll get an error on the launch call, since launch is declared only on the CoroutineScope:

**Unresolved reference: launch**

#### **runBlocking:** 
Acts as the task manager. It ensures that all coroutines started within it are completed before exiting.
#### **launch:** 
Starts a new coroutine (task). The runBlocking scope makes sure this coroutine finishes before the program exits.

### Extract function refactoring (Suspend functions)
When you perform "Extract function" refactoring on this code, you get a new function with the suspend modifier. 
Suspending functions can be used inside coroutines just like regular functions, but their additional feature is that they can, in turn, use other suspending functions (like delay in this example) to suspend execution of a coroutine.
```kotlin
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

### Scope Builder
**runBlocking** is used for blocking situations where you need to wait for the coroutine and its children to complete before continuing.
**coroutineScope** is used inside suspending functions to create a scope that waits for its coroutines to complete but doesn’t block the thread, making it more efficient for asynchronous 
- we can have multiple running blocks in  main thread and can have multiple coroutines
- we can write multiple launch in coroutine scope
```kotlin
import kotlinx.coroutines.*

// Define a suspending function that performs some asynchronous work
suspend fun performAsyncWork() = coroutineScope {
    launch{
        delay(2000L) // Simulates a long-running task
        println("Async work completed")
        }

}

fun main() {
    // Print immediately from the main function
    println("Hello")
    println("Shifa")

    // Use runBlocking to wait for the asynchronous work to complete
    runBlocking {
        // Call the suspending function which performs asynchronous work
        performAsyncWork()
    }

    // Print after runBlocking completes
    println("Exiting main")
}
```
### An explicit job﻿
A launch coroutine builder returns a Job object that is a handle to the launched coroutine and can be used to explicitly wait for its completion. For example, you can wait for completion of the child coroutine and then print "Done" string:

```kotlin
val job = launch { // launch a new coroutine and keep a reference to its Job
    delay(1000L)
    println("World!")
}
println("Hello")
job.join() // wait until child coroutine completes
println("Done")
```
## Coroutines are light-weight﻿
Coroutines are considered lightweight compared to traditional threads due to several key reasons:

### 1. **Low Overhead**

- **Threads**: Each thread in the JVM (Java Virtual Machine) requires its own stack space, typically around 1 MB to 2 MB. This means that having many threads can consume a significant amount of memory.
- **Coroutines**: Coroutines are designed to be much more memory-efficient. They do not require a dedicated stack for each coroutine. Instead, they share a small, fixed amount of memory, which allows you to run many coroutines concurrently without consuming a lot of memory.

### 2. **Efficient Context Switching**

- **Threads**: Switching between threads involves complex and resource-intensive operations managed by the operating system (OS). This includes saving and restoring the thread's state and context.
- **Coroutines**: Context switching between coroutines is managed by the Kotlin runtime and is much lighter. Coroutines can suspend and resume without the need for switching the actual OS thread, leading to less overhead and faster context switches.

### 3. **Non-blocking Suspension**

- **Threads**: A thread that performs a blocking operation (e.g., waiting for I/O) holds up its resources while waiting, preventing the OS from using that thread for other tasks.
- **Coroutines**: Coroutines use non-blocking suspension. When a coroutine is waiting (e.g., for I/O), it suspends itself and allows other coroutines or code to run on the same thread. This leads to better utilization of system resources.

### 4. **Scalability**

- **Threads**: Creating and managing a large number of threads can exhaust system resources, leading to performance degradation or crashes.
- **Coroutines**: Because they are lightweight and efficiently managed, you can create and run thousands or even millions of coroutines concurrently. This allows for highly scalable applications without hitting system limits.

### Example Code

Here’s a Kotlin example that demonstrates how you can launch a large number of coroutines efficiently:

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    // Launch 50,000 coroutines
    repeat(50000) {
        launch {
            delay(5000L) // Each coroutine waits for 5 seconds
            print(".")   // Prints a period after the delay
        }
    }
    // Wait a bit for all coroutines to complete
    delay(6000L)
    println("\nAll coroutines are done!")
}
```


### Benefits:

- **Memory Efficiency**: This code runs 50,000 coroutines concurrently without using excessive memory, showcasing the lightweight nature of coroutines.
- **Performance**: Despite the large number of coroutines, the performance remains efficient because coroutines do not consume as much memory or require heavy context switching compared to threads.

## Cancelling coroutine execution
```kotlin
val job = launch {
    repeat(1000) { i ->
        println("job: I'm sleeping $i ...")
        delay(500L)
    }
}
delay(1300L) // delay a bit (jb tk is delay ka time ni aata coroutine chalta jay ga,jesy e ye time complete hua nechy wali line print hogi or phir job cancel hujay gi..beshak abhi wo 1000 time print hua ho ya na)
println("main: I'm tired of waiting!")
job.cancel() // cancels the job
job.join() // waits for job's completion 
println("main: Now I can quit.")
```
## Output
```kotlin
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
main: Now I can quit.
```


