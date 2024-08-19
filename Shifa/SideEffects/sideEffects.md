# SideEffect
SideEffect is a Composable function that allows us to execute a side effect when its parent Composable is recomposed. A side effect is an operation that does not affect the UI directly, such as logging, analytics, or updating the external state. 
This function is useful for executing operations that do not depend on the Composable’s state or props.

## How to Use SideEffect?
To use SideEffect, we need to call it inside a Composable function and pass in a lambda that contains the side effect we want to execute. Here’s an example:
```kotlin
@Composable
fun Counter() {
    // Define a state variable for the count
    val count = remember { mutableStateOf(0) }

    // Use SideEffect to log the current value of count
    SideEffect {
        // Called on every recomposition
        log("Count is ${count.value}")
    }

    Column {
        Button(onClick = { count.value++ }) {
            Text("Increase Count")
        }

        // With every state update, text is changed and recomposition is triggered
        Text("Counter ${count.value}")
    }
}
```
In the code above, when the app is first launched, the Counter Composable function is composed and the SideEffect logs the initial 
value of count to the console. When the Button is clicked, the Text Composable is recomposed with the new value of count, but this does 
not trigger the SideEffect again.

# LaunchedEffect
LaunchedEffect is a Composable function that executes a side effect in a separate coroutine scope. 
This function is useful for executing operations that can take a long time, such as network calls or animations,
without blocking the UI thread.

### Here’s an example of how to use LaunchedEffect:

```kotlin
@Composable
fun MyComposable() {
    val isLoading = remember { mutableStateOf(false) }
    val data = remember { mutableStateOf(listOf<String>()) }

    // Define a LaunchedEffect to perform a long-running operation asynchronously
    // `LaunchedEffect` will cancel and re-launch if
    // `isLoading.value` changes
    LaunchedEffect(isLoading.value) {
        if (isLoading.value) {
            // Perform a long-running operation, such as fetching data from a network
            val newData = fetchData()
            // Update the state with the new data
            data.value = newData
            isLoading.value = false
        }
    }

    Column {
        Button(onClick = { isLoading.value = true }) {
            Text("Fetch Data")
        }
        if (isLoading.value) {
            // Show a loading indicator
            CircularProgressIndicator()
        } else {
            // Show the data
            LazyColumn {
                items(data.value.size) { index ->
                    Text(text = data.value[index])
                }
            }
        }
    }
}

// Simulate a network call by suspending the coroutine for 2 seconds
private suspend fun fetchData(): List<String> {
    // Simulate a network delay
    delay(2000)
    return listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5",)
}
```
In this example, the LaunchedEffect function executes a network call to fetch data from an API when the isLoading state variable is set to true. The function executes in a separate coroutine scope, allowing the UI to remain responsive while the operation is being performed.

The LaunchedEffect function takes two parameters: key, which is set to isLoading.value, and block, which is a lambda that defines the side effect to be executed. In this case, the block lambda calls the fetchData() function, which simulates a network call by suspending the coroutine for 2 seconds. Once the data is fetched, it updates the data state variable and sets isLoading to false, hiding the loading indicator and displaying the fetched data.

# What’s the logic behind the key parameter?
The key parameter in LaunchedEffect is used to identify the LaunchedEffect instance and prevent it from being recomposed unnecessarily.

#### --> If the value of the key parameter changes, Jetpack Compose will consider the LaunchedEffect instance as a new instance, and will execute the side effect again. 
#### --> If the value of the key parameter remains the same, Jetpack Compose will skip the execution of the side effect and reuse the previous result, preventing unnecessary recompositions.

# DisposableEffect
DisposableEffect is a Composable function that executes a side effect when its parent Composable is first rendered, and disposes of the effect when the Composable is removed from the UI hierarchy. This function is useful for managing resources that need to be cleaned up when a Composable is no longer in use, such as event listeners or animations.

Here’s an example of how to use DisposableEffect:
```kotlin
@Composable
fun TimerScreen() {
    val elapsedTime = remember { mutableStateOf(0) }

    DisposableEffect(Unit) {
        val scope = CoroutineScope(Dispatchers.Default)
        val job = scope.launch {
            while (true) {
                delay(1000)
                elapsedTime.value += 1
                log("Timer is still working ${elapsedTime.value}")
            }
        }

        onDispose {
            job.cancel()
        }
    }

    Text(
        text = "Elapsed Time: ${elapsedTime.value}",
        modifier = Modifier.padding(16.dp),
        fontSize = 24.sp
    )
}
```
In this code, we use DisposableEffect to launch a coroutine that increments the elapsedTime state value every second. We also use DisposableEffect to ensure that the coroutine is cancelled and resources used by the coroutine are cleaned up when the Composable is no longer in use.

In the cleanup function of the DisposableEffect, we cancel the coroutine using the cancel() method of the Job instance stored in job.

The onDispose function is called when the Composable is removed from the UI hierarchy, and it provides a way to clean up any resources used by the Composable. In this case, we use onDispose to cancel the coroutine and ensure that any resources used by the coroutine are cleaned up.

To check how this DisposableEffect is working let’s run the following code to see the result:
```kotlin
@Composable
fun RunTimerScreen() {
    val isVisible = remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        if (isVisible.value)
            TimerScreen()

        Button(onClick = { isVisible.value = false }) {
           Text("Hide the timer")
        }
    }
  }
```
I add a new RunTimerScreen Composable that allows the user to toggle the visibility of the TimerScreen. When the user clicks the "Hide the timer" button, the TimerScreen Composable is removed from the UI hierarchy and the coroutine is cancelled and cleaned up.

If we remove the job.cancel() call from the onDispose function, the coroutine will continue running even when the TimerScreen Composable is no longer in use, which can lead to leaks and other performance issues.

By using DisposableEffect and CoroutineScope together in this way, we ensure that the coroutine launched by CoroutineScope is cancelled and resources are cleaned up when the TimerScreen Composable is no longer in use. This prevents leaks and other performance issues, and improves the performance and stability of our app.


# When to use each
## Use Cases of DisposableEffect
1) Adding and removing event listeners
2) Starting and stopping animations
3) Bind and unbinding sensors resources such as Camera, LocationManager, etc
4) Managing database connections

## Use Cases of LaunchedEffect
1) Fetching Data from a Network
2) Performing Image Processing
3) Updating a Database

## Use Cases of SideEffect
1) Logging and Analytics
2) Performing One-Time Initialization such as setting up a connection to a Bluetooth device, loading data from a file, or initializing a library.


