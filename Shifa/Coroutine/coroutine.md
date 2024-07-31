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

## Default Dispatcher:

#### Dispatchers.Default: 
Uses a shared pool of threads optimized for CPU-bound tasks. It provides more parallelism and can run multiple coroutines concurrently on different threads.

## Cancelation is Cooperative
Coroutine cancellation is cooperative. A coroutine code has to cooperate to be cancellable. All the suspending functions in kotlinx.coroutines are cancellable. They check for cancellation of coroutine and throw CancellationException when cancelled. However, if a coroutine is working in a computation and does not check for cancellation, then it cannot be cancelled, like the following example shows:

```kotlin
val startTime = System.currentTimeMillis()
val job = launch(Dispatchers.Default) {
    var nextPrintTime = startTime
    var i = 0
    while (i < 5) { // computation loop, just wastes CPU
        // print a message twice a second
        if (System.currentTimeMillis() >= nextPrintTime) {
            println("job: I'm sleeping ${i++} ...")
            nextPrintTime += 500L
        }
    }
}
delay(1300L) // delay a bit
println("main: I'm tired of waiting!")
job.cancelAndJoin() // cancels the job and waits for its completion
println("main: Now I can quit.")

```
# Output
```kotlin
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
job: I'm sleeping 3 ...
job: I'm sleeping 4 ...
main: Now I can quit.
```
# code

```kotlin

val job = launch(Dispatchers.Default) {
    repeat(5) { i ->
        try {
            // print a message twice a second
            println("job: I'm sleeping $i ...")
            delay(500)
        } catch (e: Exception) {
            // log the exception
            println(e)
        }
    }
}
delay(1300L) // delay a bit
println("main: I'm tired of waiting!")
job.cancelAndJoin() // cancels the job and waits for its completion
println("main: Now I can quit.")

```
# output
```kotlin
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job="coroutine#2":StandaloneCoroutine{Cancelling}@64f99317
job: I'm sleeping 3 ...
kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job="coroutine#2":StandaloneCoroutine{Cancelling}@64f99317
job: I'm sleeping 4 ...
kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job="coroutine#2":StandaloneCoroutine{Cancelling}@64f99317
main: Now I can quit.
```
## Making computation code cancellable﻿
```kotlin
val startTime = System.currentTimeMillis()
val job = launch(Dispatchers.Default) {
    var nextPrintTime = startTime
    var i = 0
    while (isActive) { // cancellable computation loop
        // print a message twice a second
        if (System.currentTimeMillis() >= nextPrintTime) {
            println("job: I'm sleeping ${i++} ...")
            nextPrintTime += 500L
        }
    }
}
delay(1300L) // delay a bit
println("main: I'm tired of waiting!")
job.cancelAndJoin() // cancels the job and waits for its completion
println("main: Now I can quit.")
```
# Output
```kotlin
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
main: Now I can quit.
```
isma ye horha ha k isActive function ko jb bhi cancel mily ga ye execution krna tb e bnd kry ga phir wo simple cancel ho ya cancelAndJoin
```kotlin
val job = launch {
    try {
        repeat(10) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    } finally {
        println("job: I'm running finally")
        
    }
}
delay(1300L) // delay a bit
println("main: I'm tired of waiting!")
job.cancelAndJoin() // cancels the job and waits for its completion
println("main: Now I can quit.")
```
# Output
```kotlin
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
job: I'm running finally
main: Now I can quit.
```
# Run non-cancellable block﻿
```kotlin
val job = launch {
    try {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    } finally {
        withContext(NonCancellable) {
            println("job: I'm running finally")
            delay(1000L)
            println("job: And I've just delayed for 1 sec because I'm non-cancellable")
        }
    }
}
delay(1300L) // delay a bit
println("main: I'm tired of waiting!")
job.cancelAndJoin() // cancels the job and waits for its completion
println("main: Now I can quit.")
```
# Output
```kotlin
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
job: I'm running finally
job: And I've just delayed for 1 sec because I'm non-cancellable
main: Now I can quit.
```
# TimeOut
```kotlin
withTimeout(1300L) {
    repeat(1000) { i ->
        println("I'm sleeping $i ...")
        delay(500L)
    }
}
```
# Output
```kotlin
I'm sleeping 0 ...
I'm sleeping 1 ...
I'm sleeping 2 ...
Exception in thread "main" kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 1300 ms
 at _COROUTINE._BOUNDARY._ (CoroutineDebugging.kt:46) 
 at FileKt$main$1$1.invokeSuspend (File.kt:-1) 
 at FileKt$main$1.invokeSuspend (File.kt:-1) 
Caused by: kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 1300 ms
at kotlinx.coroutines.TimeoutKt .TimeoutCancellationException(Timeout.kt:191)
at kotlinx.coroutines.TimeoutCoroutine .run(Timeout.kt:159)
at kotlinx.coroutines.EventLoopImplBase$DelayedRunnableTask .run(EventLoop.common.kt:501)
```
## TimeOut and null function
Use the withTimeoutOrNull function that is similar to withTimeout but returns null on timeout instead of throwing an exception:

```kotlin
val result = withTimeoutOrNull(1300L) {
    repeat(1000) { i ->
        println("I'm sleeping $i ...")
        delay(500L)
    }
    "Done" // will get cancelled before it produces this result
}
```
# Output
```kotlin
I'm sleeping 0 ...
I'm sleeping 1 ...
I'm sleeping 2 ...
Result is null
```

### Diference b/w launch and async
- launch returns a Job and does not carry any resulting value
- async returns a Deferred — a light-weight non-blocking feature that represents a promise to provide a result later. You can use .await() on a deferred value to get its eventual result, but Deferred is also a Job, so you can cancel it if needed.
# Concurrent using async﻿
```kotlin
import kotlinx.coroutines.*
import kotlin.system.*

fun main() = runBlocking<Unit> {

    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
    
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}
```

## Lazy started Async
```kotlin
val time = measureTimeMillis {
    val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
    val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
    // some computation
    one.start() // start the first one
    two.start() // start the second one
    println("The answer is ${one.await() + two.await()}")
}
```
println("Completed in $time ms")
