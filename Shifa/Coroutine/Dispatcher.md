# What is a Coroutine Dispatcher?
In Kotlin, a Coroutine Dispatcher is a mechanism that determines the thread or threads a coroutine will use for its execution. It acts as a scheduler for coroutines, assigning them to specific threads for their execution. The choice of dispatcher can significantly influence the behavior and performance of your coroutine-based code.



## Here are the primary `Dispatchers` provided by Kotlin:

### **1. `Dispatchers.Default`**

- **Purpose**: Used for CPU-intensive tasks.
- **Characteristics**:
  - Provides a shared pool of threads optimized for CPU-bound work.
  - It is designed to utilize the available processors efficiently.
  - this will create the coroutine whose properties will be exactly the same as the coroutine created by GlobalScope.launch function
  - But if we use delay and then print its not compulsory its  execute in the same thread where it firstly executed.
- **Usage**: Suitable for tasks that require significant CPU processing, such as calculations or complex data processing.
- **e.g** we are calculating factorial of some number,this operation is cpu intensive so we use this dispatcher
```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {

    launch(Dispatchers.Default) { // not confined -- will work with main thread
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    
    
}
```
## Output
```kotlin

Unconfined      : I'm working in thread DefaultDispatcher-worker-1 @coroutine#2
Unconfined      : After delay in thread DefaultDispatcher-worker-1 @coroutine#2
```

### **2. `Dispatchers.IO`**

- **Purpose**: Used for I/O operations, such as reading or writing to files, network requests, or database operations.
- **Characteristics**:
  - Provides a shared pool of threads optimized for I/O-bound work.
  - It can handle a large number of concurrent I/O operations without blocking.
- **Usage**: Suitable for tasks that involve input/output operations where the work is primarily waiting for external resources.
```kotlin
  import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {

    launch(Dispatchers.IO) { // not confined -- will work with main thread
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    
    
}
```
## Output
```kotlin

Unconfined      : I'm working in thread DefaultDispatcher-worker-1 @coroutine#2
Unconfined      : After delay in thread DefaultDispatcher-worker-1 @coroutine#2
```


### **3. `Dispatchers.Main`**

- **Purpose**: Used for updating the UI or performing tasks that must run on the main thread.
- **Characteristics**:
  - This dispatcher is bound to the main thread of the application.
  - It is primarily used in Android applications to perform UI operations.
- **Usage**: Suitable for tasks that interact with the UI or need to run on the main thread.

### **4. `Dispatchers.Unconfined`**

- **Purpose**: Used for coroutines that are not confined to any specific thread.
- **Characteristics**:
  - It starts the coroutine on the current thread but may resume it on any thread.
  - It is typically used for debugging or for scenarios where you don’t need specific thread confinement.
  - This coroutine is going to inherit the coroutine context from the immediate parent but, after some delay it will not execute the immediate parent ,but execute another thread which is assigned from the shared pool of the thread
- **Usage**: Suitable for coroutines where thread confinement is not necessary or when working with debugging.
```kotlin
  import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {

    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    
    
}
```
## Output
```kotlin
Unconfined      : I'm working in thread main @coroutine#2
Unconfined      : After delay in thread kotlinx.coroutines.DefaultExecutor @coroutine#2
```
### **5. `Confined Dispatcher`**
urpose: 
Characteristics:


- **Purpose**: Used for tasks that need to run on a specific thread or within a specific context.
- **Characteristics**:
  - Provides a way to confine coroutines to a specific thread or executor, unlike the other dispatchers which use shared pools of threads.
  - It is not automatically optimized for I/O or CPU-bound tasks but allows you to control the execution context explicitly.
  - Useful for scenarios where tasks need to run on a particular thread or a single-threaded executor, such as in some testing or debugging scenarios.
```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {

    launch { 
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    
    
}
```
## Output
```kotlin

Unconfined      : I'm working in thread main @coroutine#2
Unconfined      : After delay in thread main @coroutine#2
```
### **Summary**

- **`Dispatchers.Default`**: Optimized for CPU-bound tasks with a shared thread pool.
- **`Dispatchers.IO`**: Optimized for I/O-bound tasks with a larger thread pool.
- **`Dispatchers.Main`**: Used for tasks that need to run on the main thread, typically for UI updates.
- **`Dispatchers.Unconfined`**: Not confined to any specific thread, used for scenarios where thread confinement is not necessary.

These `Dispatchers` help manage coroutines efficiently by directing them to the appropriate execution context based on the task's nature.
