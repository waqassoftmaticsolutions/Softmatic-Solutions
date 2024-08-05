# Flows
Kotlin Flows are a part of Kotlin's Coroutines library designed to handle asynchronous streams of data. Flows are particularly useful in scenarios where data needs to be emitted and consumed over time, such as network responses, database updates, or user interactions.
## Key Features
  1) Asynchronous: Flows allow for asynchronous operations, enabling you to work with data without blocking the main thread.
  2) Cold Streams: Flows are cold, meaning they do not emit any values until they are collected. Each new collector triggers the emission of values from the beginning.

## Purpose of Flows
  1) Asynchronous Data Streams:
  2) Non-blocking Operation
     ```kotlin
      fun fetchData(): Flow<Int> = flow {
        for (i in 1..5) {
            delay(1000) // Non-blocking delay
            emit(i)
            }
        }
     ```
  3) Cold Streams: Flows are cold streams, meaning they do not produce data until they are collected.
  4) Operators for Transformation and Filtering
     ```kotlin
      fun main() = runBlocking {
        val flow = (1..5).asFlow()
            .filter { it % 2 == 0 }
            .map { it * it }
    
        flow.collect { println(it) } // Prints 4, 16
      }
     ```

## Types of Flow
1) Flow: The basic type for cold asynchronous streams.
2) StateFlow: A state holder that emits updates to its state to collectors, always holding the latest value.
3) SharedFlow: A hot flow that can emit values to multiple collectors simultaneously.

### Basic Flow Syntax
```kotlin
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
fun simpleFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(10000) // Simulating asynchronous work
        emit(i) // Emitting values
    }
}
fun main() = runBlocking {
    simpleFlow().collect { value ->
        println(value)
    }
}
```
Code inside a flow { ... } builder block can suspend.
#### Note
1) Values are emitted from the flow using an emit function.
2) Values are collected from the flow using a collect function.
```kotlin
fun producer():Flow<Int> = flow {
    val list = listOf(1,2,3,4,5)
    list.forEach{
        delay(1000)
        emit(it)
    }
}
fun main() = runBlocking<Unit> {
  launch{
          val data = producer()
          data.collect{
              println("Data: $it")
          }
      }
}
```

## Kotlin Flows and Operators
In an android application when you have done a network call on UI you have to show that the data is loading. In this we will use Koltin Events in flows.
  1) onStart() --> This will me called only at the start of the flows
  2) onCompletion() --> This will be called only at the completion of the flow
  3) onEach() --> This will be called on every event when the data will be emit in the flow
```kotlin
//Producer
fun producer():Flow<Int> = flow {
    val list = listOf(1,2,3,4,5)
    list.forEach{
        emit(it)
    }
}


fun main() = runBlocking<Unit> {
    launch{
        //producer().collect { value -> println(value) }
        producer()
            .onStart { println("producer started") }
            .onCompletion { println("producer completed") }
            .onEach { println("Emitting $it") }
            .collect { value -> println(value) }
    }
}
```
## Time out
```kotlin
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*        
fun simple(): Flow<Int> = flow { 
    for (i in 1..3) {
        delay(100)          
        println("Emitting $i")
        emit(i)
    }
}
fun main() = runBlocking<Unit> {
    withTimeoutOrNull(450) { // Timeout after 250ms 
        simple().collect { value -> println(value) } 
    }
    println("Done")
}
```

### Flow Builders
There are two types of flow builders, Convert 
  1) flowOf()
  2) asFlow()
  3) flow{}
```kotlin
  fun main() = runBlocking<Unit> {
      println("Testing asFlow()")
      (1..3).asFlow().collect { value -> println(value) }
      println("Testing flowOf()")
      val flow = flowOf(4,5,6,7,8,9,1)
      flow.filter { it%2 == 0 }.collect{value -> println(value)}
  }
```

### Flow operators﻿
1) Terminal Operators
2) Non Terminal Operators
#### Terminal Operators
Terminal operators are the operators that start the collection of a Flow and produce a final result.  These operators are called at the end of the Flow chain and are responsible for triggering the flow of data. Common terminal operators are
  1) collect -> Collects the emitted values from the Flow and performs an action for each value.
  2) toList -> Collects the emitted values into a list.
  3) first -> Returns the first emitted value and cancels the flow collection.
  4) single -> Returns the only emitted value if the Flow emits exactly one value, otherwise throws an exception.
  5) reduce -> Find sum of all the flow. val sum = flowOf(1, 2, 3,4).reduce { accumulator, value -> accumulator + value  }
```kotlin
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*        
fun main() = runBlocking<Unit> {
    val sum = (1..5).asFlow()
        .map { it * it } // squares of numbers from 1 to 5                           
        .reduce { a, b -> a + b } // sum them (terminal operator)
    println(sum)
    println("-----------------")
    val singleValue = flowOf(1).single()
    println(singleValue)
    println("-----------------")
    val list = flowOf(1,2,3,4,5).toList()
    println(list)
}
```
#### Non-Terminal Operators
Non-terminal operators, also known as intermediate operators, are used to transform, filter, or otherwise process the data emitted by a Flow.
  1) map -> Transforms each emitted value.
  2) filter -> Filters emitted values based on a predicate
  3) take -> Limits the number of emitted values.
```kotlin
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*        
suspend fun performRequest(request: Int): String {
    delay(1000)
    return "response $request"
}
fun numbers(): Flow<Int> = flow {
    try {                          
        emit(1)
        emit(2) 
        println("This line will not execute")
        emit(3)    
    } finally {
        println("Finally in numbers")
    }
}
fun main() = runBlocking<Unit> {
    (1..4).asFlow()
    	.filter{it%2==0}
        .map { performRequest(it) }
        .collect { println(it) }
       println("-------------------")
    (1..3).asFlow()
        .transform { request ->
            emit("Making request $request") 
            emit(performRequest(request)) 
        }
        .collect { response -> println(response) }
       println("-----------------")
       numbers() 
        .take(2)
        .collect { value -> println(value) }
}
```

### Flows are sequential
```kotlin
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
fun main() = runBlocking<Unit> {
    (1..5).asFlow()
        .filter {
            println("Filter $it")
            it % 2 == 0              
        }              
        .map { 
            println("Map $it")
            "string $it"
        }.collect { 
            println("Collect $it")
        }    
                  
}
```

## Composing multiple flows﻿
#### Zip
Just like the Sequence.zip extension function in the Kotlin standard library, flows have a zip operator that combines the corresponding values of two flows:
```kotlin
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking<Unit> {                                                             
    val nums = (1..3).asFlow() // numbers 1..3
    val strs = flowOf("one", "two", "three") // strings 
    nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string
        .collect { println(it) } // collect and print

}
```
