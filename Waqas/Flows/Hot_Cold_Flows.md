# Difference Between Hot And Cold Flows
<table border="1">
  <tr>
    <th>Hot Flows</th>
    <th>Cold Flows</th>
  </tr>
  <tr>
    <td><strong>Shared Emission</strong></td>
    <td>The flow can emit the values regardless of the presence of collectors.</td>
    <td>The flows do not start emitting the values until a terminal operator is called.</td>
  </tr>
  <tr>
    <td><strong>No. of Consumers</strong></td>
    <td>Any number of consumers can collect the flow, and each will receive the full sequence of values from the start.</td>
    <td>Any number of consumers can collect the flow, but they will only receive values emitted after they start collecting.</td>
  </tr>
  <tr>
    <td><strong>Late Joiners</strong></td>
    <td>Consumers do not interfere with each other; they each have their own instance of the flow's emission.</td>
    <td>Consumers that join late will miss the previously emitted values unless the hot flow is configured to replay them (e.g., SharedFlow with a replay buffer).</td>
  </tr>
</table>

## Both Examples
```kotlin
package com.example.practiceandroid
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Cold Flows
        fun coldFlow(): Flow<Int> = flow {
            Log.d("MainActivity", "Flow started")
            for (i in 1..3) {
                delay(100)
                emit(i)
            }
        }

        //Hot FLows --> Shared Flows
        fun hotFlows(): Flow<Int> {
            val sharedFlow = MutableSharedFlow<Int>(1)
            //delay(100)
            GlobalScope.launch {
                for (i in 1..5) {
                    sharedFlow.emit(i)
                    delay(100)
                }
            }
            return sharedFlow
        }

        // Hot Flows --> State Flows
        fun hotStateFlows(): Flow<Int> {
            val sharedFlow = MutableStateFlow<Int>(-1)
            //delay(100)
            GlobalScope.launch {
                for (i in 1..5) {
                    sharedFlow.emit(i)
                    delay(100)
                }
            }
            return sharedFlow
        }


//        runBlocking{
//            Log.d("MainActivity", "Calling simple function...")
//            val flow = simple()
//            Log.d("MainActivity", "Calling collect...")
//            flow.collect { value -> Log.d("MainActivity", value.toString()) }
//            Log.d("MainActivity", "Calling collect again...")
//            flow.collect { value -> Log.d("MainActivity", value.toString()) }
//        }

        Log.d("MainActivity", "Cold Flows Running")
        runBlocking {
            launch {
                Log.d("MainActivity", "Consumer 1")
                coldFlow().collect { value -> Log.d("MainActivity", value.toString()) }
                //delay(1000)
            }
            launch {
                Log.d("MainActivity", "Consumer 2")
                coldFlow().collect { value -> Log.d("MainActivity", value.toString()) }
            }
            launch {
                delay(1000)
                Log.d("MainActivity", "Consumer 3")
                coldFlow().collect { value -> Log.d("MainActivity", value.toString()) }
            }
        }
        Log.d("MainActivity", "Hot Flows Running")
        runBlocking {
            launch {
                val result = hotFlows()
                Log.d("MainActivity", "Consumer 1")
                result
                    .onStart { Log.d("MainActivity", "Consumer 1 Started") }
                    .onCompletion { Log.d("MainActivity", "Consumer 1 Completed") }
                    .onEach { Log.d("MainActivity", "Consumer 1 Emiiting $it") }
                    .collect { value -> Log.d("MainActivity", value.toString()) }
            }
            launch {
                val result = hotFlows()
                delay(500)
                Log.d("MainActivity", "Consumer 2")
                result
                    .onStart { Log.d("MainActivity", "Consumer 2 Started") }
                    .onCompletion { Log.d("MainActivity", "Consumer 2 Completed") }
                    .onEach { Log.d("MainActivity", "Consumer 2 Emiiting $it") }
                    .collect { value -> Log.d("MainActivity", value.toString()) }
            }
            launch {
                val result = hotFlows()
                Log.d("MainActivity", "Consumer 3")
                result
                    .onStart { Log.d("MainActivity", "Consumer 3 Started") }
                    .onCompletion { Log.d("MainActivity", "Consumer 3 Completed") }
                    .onEach { Log.d("MainActivity", "Consumer 3 Emiiting $it") }
                    .collect { value -> Log.d("MainActivity", value.toString()) }
            }
        }
    }
}
```
## State Flow and Shared Flow
<table border="1">
  <tr>
    <th>State Flows</th>
    <th>Shared Flows</th>
  </tr>
  <tr>
    <td><strong>Statement</strong></td>
    <td>StateFlow is a state-holder observable flow that emits the current and new state updates to its collectors.</td>
    <td>SharedFlow is a hot flow that emits values to multiple collectors without holding any particular state. </td>
  </tr>
  <tr>
    <td><strong>State Holder</strong></td>
    <td>It always has a current value. You can access this value via the value property.</td>
    <td>Unlike StateFlow, it does not hold a state and does not have an initial value.</td>
  </tr>
  <tr>
    <td><strong>Replay of Latest Value</strong></td>
    <td>Only the latest value is replayed to new collectors.</td>
    <td>You can configure the number of most recent values to be replayed to new collectors using the replay parameter.</td>
  </tr>
  <tr>
    <td><strong>Use Cases</strong></td>
    <td>UI State Management</td>
    <td>Event Broadcasting, One-Time Events, Multicast Events</td>
  </tr>
</table>

### Example State FLow
```Kotlin
package com.example.practiceandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fun stateFLows():Flow<String>{
            val stateFlow = MutableStateFlow("Waqas")
            GlobalScope.launch{
                delay(1000)
                stateFlow.emit("Ehtisham")
                delay(1000)
                stateFlow.emit("Umar")
                delay(1000)
                stateFlow.emit("Qadeer")
            }
            return stateFlow
        }

        runBlocking {
            launch{
                var result = stateFLows()
                result
                    .onStart { Log.d("MainActivity", "Flow 1 started") }
                    .onCompletion { Log.d("MainActivity", "Flow 1 Completed") }
                    .onEach { Log.d("MainActivity", "Emitting C1 $it") }
                    .collect{value -> Log.d("MainActivity", value.toString())}
            }
            launch{
                var result = stateFLows()
                delay(2000)
                result
                    .onStart { Log.d("MainActivity", "Flow 2 started") }
                    .onCompletion { Log.d("MainActivity", "Flow 2 Completed") }
                    .onEach { Log.d("MainActivity", "Emitting C2 $it") }
                    .collect{value -> Log.d("MainActivity", value.toString())}
            }
        }
    }
}
```
