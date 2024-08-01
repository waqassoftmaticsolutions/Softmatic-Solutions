# What is the scope of coroutines?
As the name implies it helps to define a scope for coroutines, meaning basically it defines the lifespan of the coroutines. The scope keeps track of all the coroutines, which helps us to When a scope cancels, coroutines get canceled. Every coroutine builder like launch, async, etc. is an extension of CoroutineScope. A coroutine cannot be launched without scope.
Coroutines scope helps convention for structure concurrency, so basically, the context of scope contains instances of job which help us to enforce structure concurrency.
![image](https://miro.medium.com/v2/resize:fit:720/format:webp/1*e8o-QWBOC4JLjFD6UuGsSg.png)
The scope are following
  1) GlobalScope
  2) LifecycleScope
  3) ViewModelScope
  4) CoroutinesScope
  5) MainScope
  6) 
## GlobalScope
When the coroutines launch with the GlobalScope, then the life of coroutines binds to the application life cycle. It means coroutines launched with global scope live as long as the application is destroyed. @DelicateCoroutinesApi annotation. From now on, the use of GlobalScope isn’t recommended. 
##### Usage
It’s often used for top-level coroutines that do not need to be tied to any specific lifecycle.
  1) For tasks that need to run for a long time and should not be affected by the lifecycle of any particular component, such as background processing or periodic tasks
  2) When you need to launch a coroutine that is independent of the application's lifecycle, such as a network request or a computation that is not tied to the UI or any specific part of the application.
##### Code Example
```kotlin
import kotlinx.coroutines.*
fun main(){
    GlobalScope.launch {
        println("Running at: ${Thread.currentThread().name}")
        val result = performLongRunningTask()
            println("Task completed: $result")
    }
    Thread.sleep(5000)
}
suspend fun performLongRunningTask(): String {
    delay(3000)
    return "Task Result"
}
```
##### Output
```text
Running at: DefaultDispatcher-worker-1 @coroutine#1
Task completed: Task Result
```

## LifecycleScope
When the coroutines launch with the lifecycleScope, then the life of the coroutine ties with the LifeCycle of Activity or Fragment. It means When the activity or fragment is destroyed then the coroutine also dies. 
  1) To acces **CoroutineScope of the Lifecycle of activity** use lifecycle.coroutineScope
  2) To acces **CoroutineScope of the Lifecycle fragment** use lifecycleOwner.lifecycleScope
##### Usage
We can use it in the following terms:
  1) We can use lifecycleScope to perform some UI tasks for example precomputed text and showingToolTipScreen(Success, Error, or Intro slider screen).
##### Code Example
```kotlin
import kotlinx.coroutines.*
class LifecycleOwner {
    private val job = Job()
    val lifecycleScope = CoroutineScope(Dispatchers.Default + job)
    fun onDestroy() {
        job.cancel()
    }
}
suspend fun fetchData(): String {
    delay(2000)
    return "Fetched Data"
}
fun updateUI(data: String) {
    println("Updating UI with data: $data")
}
fun main() {
    val lifecycleOwner = LifecycleOwner()
    println("Thread is:  ${Thread.currentThread().name}")
    lifecycleOwner.lifecycleScope.launch {
        println("Thread is:  ${Thread.currentThread().name}")
        val data = fetchData()
        updateUI(data)
    }
    Thread.sleep(3000)
    println("Destroying lifecycle owner...")
    lifecycleOwner.onDestroy()
    Thread.sleep(3000)
}
```
##### Output
```text
Thread is:  main
Thread is:  DefaultDispatcher-worker-1
Updating UI with data: Fetched Data
Destroying lifecycle owner...
```
## ViewModelScope
ViewModelScope is defined for every ViewModel. Coroutines in this scope are useful when there is work that should only be done when a ViewModel is active. Using viewModelScope coroutine gets automatically canceled if ViewModel is cleared.
##### Usage
  1) We can use viewModelScope to perform operations like Fetching data from the server, saving records in the database.
  2) If we are using a ViewModel and we want to execute a task in the background like performing a network request call. For those kinds of tasks, we will use viewModelScope.
##### Code Example
```kotlin
import kotlinx.coroutines.*
class ViewModel {
    private val viewModelJob = Job()
    val viewModelScope = CoroutineScope(Dispatchers.Default + viewModelJob)
    fun fetchData() {
        viewModelScope.launch {
            val data = "Fetched Data"
            println("Data fetched: $data")
            delay(1000)
            println("Data fetched 1: $data")
        }
    }
    fun onCleared() {
        viewModelJob.cancel()
        println("ViewModel is cleared, coroutines are cancelled.")
    }
}
fun main() {
    val viewModel = ViewModel()
    viewModel.fetchData()
    runBlocking {
        delay(2500)
        viewModel.onCleared()
        delay(2000)
    }
}
```
##### Output
```text
Data fetched: Fetched Data
Data fetched 1: Fetched Data
ViewModel is cleared, coroutines are cancelled.
```

## CoroutinesScope
This will allow you to define custom scopes with your own coroutines context.
So basically to start a coroutine requires a CoroutineScope and this will take CoroutineContext as a parameter.
##### Usage
  1) Lifecycle Management
##### Code Example
```kotlin
import kotlinx.coroutines.*
class MyCoroutineScope : CoroutineScope {
    private val job = Job()
    override val coroutineContext = Dispatchers.Default + job
    fun fetchData() {
        launch {
            delay(2000)
            val data = "Fetched Data"
            println(data)
        }
    }
    fun clear() {
        job.cancel()
    }
}
fun main() {
    val myScope = MyCoroutineScope()
    myScope.fetchData()
    runBlocking {
        delay(2000)
        println("Clearing scope...")
        myScope.clear()
        delay(3000)
    }
}
```
##### Output
```text
Clearing scope...
Fetched Data
```

## MainScope
It will launch coroutines within the main thread i.e UI thread. It uses Dispatcher.Main for its coroutines.
##### Usage
  1) UI Updates
  2) Life Cycle management
##### Code Example
```kotlin
import kotlinx.coroutines.*
class MyActivity {
    private val scope = CoroutineScope(Dispatchers.Default + Job())
    fun fetchData() {
        scope.launch {
            delay(1000)
            val data = "Fetched Data"
            println("Data fetched: $data")
        }
    }
    fun onDestroy() {
        scope.cancel()
    }
}
fun main() {
    val activity = MyActivity()
    activity.fetchData()
    runBlocking {
        delay(1000)
        println("Activity is being destroyed...")
        activity.onDestroy()
        delay(3000)
    }
}
```

##### Output
```text
Activity is being destroyed...
Data fetched: Fetched Data
```
