# Testing Coroutines
```kotlin
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import com.mongodb.client.MongoCollection
import org.bson.Document
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val numbers = mutableListOf(100000000)
    val fetchDataDeferred = async(Dispatchers.IO) {
        val startTime = System.currentTimeMillis()
        println("Before fetch: $startTime ms")
        val connectionString = "mongodb+srv://shifafatima:12345@cluster0.wki1hrt.mongodb.net/<sample_mflix>?retryWrites=true&w=majority"
        val client = MongoClients.create(connectionString)
        val database: MongoDatabase = client.getDatabase("sample_mflix")
        val collection: MongoCollection<Document> = database.getCollection("movies")
        val documents = collection.find().toList()
        client.close()
        val endTime = System.currentTimeMillis()
        println("After fetch: $endTime ms")
        documents
    }
    val calculateSumDeferred = async(Dispatchers.Default) {
        val startTime = System.currentTimeMillis()
        for(i in 1..100000000){
            numbers.add(i)
        }
        println("Before sum: $startTime ms")
        val sum = numbers.sum()
        val endTime = System.currentTimeMillis()
        println("After sum: $endTime ms")
        println("Time taken to calculate sum: ${endTime-startTime} ms")
        sum
    }
    val fetchDataTime = measureTimeMillis {
        fetchDataDeferred.await()
    }
    val sum = calculateSumDeferred.await()
    val sumTime = measureTimeMillis {
        sum
    }
    println("Time taken to fetch data: $fetchDataTime ms")
    println("Sum of numbers: $sum")
}
```

# Without Coroutines
```kotlin

```
