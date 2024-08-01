# CoroutineScope
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




















