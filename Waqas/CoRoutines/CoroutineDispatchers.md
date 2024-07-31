# Coroutine Dispatchers
Coroutine dispatchers in Kotlin determine the thread or threads on which a coroutine runs. It acts as a scheduler for coroutines, assigning them to specific threads for their execution. The choice of dispatcher can significantly influence the behavior and performance of your coroutine-based code.
## Types of Dispatchers in Kotlin
There are several built-in coroutine dispatchers in Kotlin, each serving different purposes:
1) Default Dispatcher
2) Unconfined Dispatcher
3) Main Dispatcher
4) IO Dispatcher

### Default Dispatcher
This dispatcher is optimized for CPU-intensive work. It is backed by a pool of threads, and the number of threads is typically equal to the number of CPU cores.Gets its own context of global level . 
Execute in separate background thread and even after delay tis may have same or different background thread.
CPU-intensive tasks are operations that require significant processing power and computational resources. These tasks primarily utilize the CPU rather than waiting for external events like I/O operations. Examples of CPU-intensive tasks include:
  1) Complex Calculations and mathematical operations
  2) Data processing
  3) ML and AI {Training ML models, running inference on larger datasets}
  4) Algorithmic tasks
#### Code Example
```kotlin
import kotlinx.coroutines.*
fun main() {
    runBlocking {
        launch(Dispatchers.Default) {
            println("Running on thread: ${Thread.currentThread().name}")
            delay(3000)
            println("Running on thread: ${Thread.currentThread().name}")
        }
        
    }
}
```
#### Output
```Text
Running on thread: DefaultDispatcher-worker-1 @coroutine#2
Running on thread: DefaultDispatcher-worker-1 @coroutine#2
```

### Unconfined Dispatcher
This dispatcher runs the coroutine in the caller thread until the first suspension point. After suspension, it resumes execution in the appropriate thread, which might be different from the original caller thread.
The coroutine starts execution in the caller thread, but it can resume in a different thread after suspension.
 Fast, non-blocking operations, debugging, and learning coroutine behavior.
### Code Example
```Kotlin
import kotlinx.coroutines.*
fun main() {
    runBlocking{
        println("Main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        launch(Dispatchers.Unconfined) {
            println("Unconfined: I'm working in thread ${Thread.currentThread().name}")
            delay(500)
            println("Unconfined: After delay in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            println("Default: I'm working in thread ${Thread.currentThread().name}")
            delay(500)
            println("Default: After delay in thread ${Thread.currentThread().name}")
        }
    }
}
```
#### Output
```Text
Main runBlocking: I'm working in thread main @coroutine#1
Unconfined: I'm working in thread main @coroutine#2
Default: I'm working in thread DefaultDispatcher-worker-1 @coroutine#3
Unconfined: After delay in thread kotlinx.coroutines.DefaultExecutor @coroutine#2
Default: After delay in thread DefaultDispatcher-worker-2 @coroutine#3
```

### Main Dispatcher
This dispatcher is designed for UI-related tasks in Android applications. It ensures that the coroutine runs on the main/UI thread, making it safe to update the user interface.This dispatcher is essential for Android development because it ensures that coroutine code runs on the main thread, which is required for any UI-related operations.
We have to use it in 
  1) UI Updates: Updating TextViews, Buttons, or other UI components.
  2) Handling user interactions: : Responding to user input, such as clicks or gestures.
  3) Navigating between screens: Starting activities, showing dialogs, or navigating fragments.
  4) Displaying toast messages: Showing temporary messages to the user.
### Code Example
```kotlin
import kotlinx.coroutines.*
import kotlin.coroutines.*
fun main() = runBlocking {
    // Define a new Main dispatcher for the playground
    val mainDispatcher = newSingleThreadContext("UI thread")
    // Launch a coroutine in the simulated Main (UI) thread
    launch(mainDispatcher) {
        println("Starting coroutine in thread: ${Thread.currentThread().name}")
        delay(1000) // Simulate a long-running task
        println("Updating UI in thread: ${Thread.currentThread().name}")
    }
    // Back to the main thread
    println("Back to the main thread: ${Thread.currentThread().name}")
}
```
#### Output
```Text
Back to the main thread: main @coroutine#1
Starting coroutine in thread: UI thread @coroutine#2
Updating UI in thread: UI thread @coroutine#2
```

### IO Dispatcher
Tasks that involve IO operations, such as reading from disk, network operations, or database access, benefit from a large pool of threads that can handle blocking operations. Using Dispatchers.IO allows these tasks to run efficiently without blocking the main thread.
W have to use it in following
  1) Network Requests: Fetching data from an API.
  2) File Operations: Reading from or writing to files on disk.
  3) Database Operations: Executing database queries.
### Code Example
```kotlin
import kotlinx.coroutines.*
import kotlin.random.Random
suspend fun fetchNetworkData(): String {
    delay(1000) // Simulate network delay
    return "Fetched data: ${Random.nextInt(100)}"
}
fun main() = runBlocking {
    // Start a coroutine in the main thread
    println("Starting coroutine in thread: ${Thread.currentThread().name}")
    // Perform network request in IO dispatcher
    val data = withContext(Dispatchers.IO) {
        fetchNetworkData()
    }
    // Back to the main thread with the result
    println("Back to the main thread: ${Thread.currentThread().name}")
    println("Received data: $data")
}
```
#### Output
```Text
Starting coroutine in thread: main @coroutine#1
Back to the main thread: main @coroutine#1
Received data: Fetched data: 2
```
