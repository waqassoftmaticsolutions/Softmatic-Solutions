

# There are multiple coroutine scope
- GlobalScope
- Lifecycle Scope
- ViewModelScope
- CoroutinesScope
- MainScope

## 1. GlobalScope

When the coroutines launch with the GlobalScope, then the life of coroutines binds to the application life cycle. It means coroutines launched with global scope live as long as the application is destroyed.
### Usage
 - Use for background tasks that should outlive any particular component or activity.
- Avoid using for tasks that should be canceled when a specific lifecycle ends, such as UI updates or component-specific tasks.
- Typically used for top-level or long-lived coroutines, not recommended for most application-level code due to lack of lifecycle management.

### Real-time use case:

When we want to execute a task that operates during the whole application’s lifetime. For example, fetching configuration from the server or fetching user profile.
```kotlin
import kotlinx.coroutines.*
import java.io.File
import kotlin.random.Random

// Function to simulate fetching updates from a server
suspend fun fetchUpdates(): String {
    delay(1000L) // Simulate network delay
    return "Update ${Random.nextInt(100)}" // Simulated update data
}

// Function to log updates to a file
fun logUpdate(update: String) {
    val logFile = File("updates.log")
    logFile.appendText("$update\n")
}

fun main() {
    // Launch a long-lived coroutine in GlobalScope
    GlobalScope.launch {
        while (true) {
            val update = fetchUpdates()
            logUpdate(update)
            println("Logged update: $update")
            delay(5000L) // Wait 5 seconds before fetching the next update
        }
    }

    // Keep the main thread alive for a while to see the logs
    Thread.sleep(30000L) // Sleep for 30 seconds
    println("Main thread finished")
}
```
## 2. lifecycle scope:

When the coroutines launch with the lifecycleScope, then the life of the coroutine ties with the LifeCycle of Activity or Fragment. It means When the activity or fragment is destroyed then the coroutine also dies.

#### To access the CoroutineScope of the Lifecycle of activity use lifecycle.coroutineScope or for fragment use lifecycleOwner.lifecycleScope.

### Real-time use case:

1. We can use lifecycleScope to perform some UI tasks for example precomputed text and showingToolTipScreen(Success, Error, or Intro slider screen).
```kotlin
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        // Launching a coroutine within the lifecycle scope
        lifecycleScope.launch {
            // Simulate fetching data with a delay
            val data = fetchData()
            // Update the UI with the fetched data
            textView.text = data
        }
    }

    private suspend fun fetchData(): String {
        // Simulate a long-running operation
        delay(2000)
        return "Fetched Data"
    }
}

```
## Using LifecycleScope we can also use special launch condition:
1. launchWhenCreated will launch coroutine if the lifecycle is at least on create state and gets suspended if it’s on destroy state.
2. launchWhenStarted will launch coroutine if the lifecycle is at least on start state and gets suspended if it’s on stop state.
3. launchWhenResumed will launch coroutine if the lifecycle is at least on resume state and gets suspended if it’s on pause state.
```kotlin
lifecycleScope.launchWhenResumed {
  println("loading..")
  delay(3000)
  println("job is done")
}

For instance, code snippet above will be executed if the lifecycle owner (Activity/Fragment) is at least onResumed. If the lifecycle owner is still onCreated or onStarted the coroutine will not be executed. By default LifecycleScope will have Dispatchers.Main as default CoroutineContext.
```



## 3. View-Model Scope
ViewModelScope is similar to LifecycleScope except it happens on ViewModel. Without ViewModelScope usually we want to cancel the coroutine on clear state. But using this ViewModelScope we don’t need to cancel coroutine manually, we can rely onViewModelScope to cancel automatically if the ViewModel is about to be cleared.
```kotlin
viewModelScope.launch {
  println("loading..")
  delay(3000)
  println("job is done")
}
```
Example usage of ViewModelScope can be seen on code snippet above. Same as LifecycleScope, by default ViewModelScope will have Dispatchers.Main as default CoroutineContext.
## Real-Life Application:
ViewModelScope is particularly useful for tasks such as:

- Fetching data from a network or database in response to user actions.
- Performing background computations or processing.
- Handling asynchronous operations that should be scoped to the ViewModel lifecycle, ensuring that tasks are cleaned up when the UI is no longer active.
- I am using MVVM architecture and in the ViewModel doing some background tasks like performing a network request call. So we can use viewModelScope here while launching coroutines.

##  4. CoroutineScope
- Suppose we want to run an asynchronous long running task. If the task goes well then it will finish and terminated as expected. But if the task somehow doesn’t go well, keep running for a long time and it’s still running when the user doesn’t intend to use it anymore then we need to stop it at some point because we don’t want the task to waste user’s cpu and memory resource. 
In coroutine we can avoid this problem by keep track the task and limit its lifetime using CoroutineScope.

- When a scope cancels, coroutines get canceled. Every coroutine builder like launch, async, etc. is an extension of CoroutineScope. A coroutine cannot be launched without scope.

```kotlin
class ExampleActivity: AppCompatActivity() {
  ...
  private lateinit var mCoroutineScope: CoroutineScope
  ...
  private fun coroutineTest() {
      mCoroutineScope = CoroutineScope(Dispatchers.Main)
      mCoroutineScope.launch {
        println("loading..")
        delay(3000)
        println("job is done")
      }
  }
  override fun onDestroy() {
    super.onDestroy()
    if(::mCoroutineScope.isInitialized && mCoroutineScope.isActive){                    
      mCoroutineScope.cancel() 
    }
}
```
This will allow you to define custom scopes with your own coroutines context.

So basically to start a coroutine requires a CoroutineScope and this will take CoroutineContext as a parameter.

## Real-time use case:

In some situations when we want to control the lifecycle of the Coroutines we launch so that we can cancel them and handle any exceptions.

### E.g:
```kotlin
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() {
    // Create a custom Job
    val job = Job()

    // Define a CoroutineContext with IO Dispatcher and custom Job
    val context: CoroutineContext = job + Dispatchers.IO

    // Create a custom CoroutineScope with a specific CoroutineContext and name
    val scope = CoroutineScope(context + CoroutineName("MyCoroutine"))

    // Launch a coroutine within the custom scope
    scope.launch {
        println("${coroutineContext[CoroutineName]} is running in thread ${Thread.currentThread().name}")

        // Simulate a background task
        delay(1000L)
        println("Background task completed")

        // Check the job status
        if (isActive) {
            println("Coroutine is still active")
        }
    }

    // Wait for a bit to see the coroutine output
    Thread.sleep(1500L)

    // Cancel the scope when done
    scope.cancel()
    println("Custom scope canceled")
}

```
# Output
```kotlin
CoroutineName(MyCoroutine) is running in thread DefaultDispatcher-worker-1 @MyCoroutine#1
Background task completed
Coroutine is still active
Custom scope canceled
```

## 5. MainScope()
`MainScope` is a coroutine scope that is tied to the main thread (UI thread) and uses the `Dispatchers.Main` dispatcher. This is particularly useful for tasks that need to run on the main thread, such as updating the UI or interacting with components that require main-thread access.

### When to Use `MainScope`:

1. **UI Updates**: Use `MainScope` for coroutines that need to update the UI directly or perform operations that must be executed on the main thread.

2. **Lifecycle Management**: `MainScope` is often used in conjunction with lifecycle-aware components to manage coroutines that are tied to the lifecycle of a `LifecycleOwner`. However, `lifecycleScope` is typically preferred for this purpose as it handles lifecycle events automatically.

3. **Short-Lived Tasks**: Ideal for short-lived tasks that need to be executed on the main thread and do not require manual management of cancellation or lifecycle.

### Example Using `MainScope`:

Here’s a simple example of how you might use `MainScope` in a Kotlin application:

```kotlin
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val mainScope = MainScope() // Create a MainScope instance
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        // Launching a coroutine within MainScope
        mainScope.launch {
            // Simulate a delay to mimic a long-running operation
            delay(2000)
            // Update the UI with fetched data
            textView.text = "Fetched Data"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel all coroutines in MainScope to avoid memory leaks
        mainScope.cancel()
    }
}
```

### Explanation:

1. **Creating `MainScope`**:
   - `MainScope` is instantiated, which sets up a coroutine scope using `Dispatchers.Main`.

2. **Launching a Coroutine**:
   - A coroutine is launched within `mainScope` to perform a background task (simulated with `delay`) and then update the UI with the result.
   - Since `mainScope` uses `Dispatchers.Main`, the coroutine runs on the main thread, ensuring UI updates are performed on the correct thread.

3. **Cleanup**:
   - In the `onDestroy` method, `mainScope.cancel()` is called to cancel all ongoing coroutines and prevent memory leaks. This is important to ensure that no coroutines are left running when the `Activity` is destroyed.











