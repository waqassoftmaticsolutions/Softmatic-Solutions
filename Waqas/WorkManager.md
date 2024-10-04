## WorkManager
A backwards compatible, flexible, and simple library for deferrable background work. WorkManager is the recommended task scheduler on Android for deferrable work, with a guarantee to execute.
WorkManager is part of Android Jetpack and an Architecture Component for background work that needs a combination of opportunistic and guaranteed execution. Opportunistic execution means that WorkManager does your background work as soon as it can. Guaranteed execution means that WorkManager takes care of the logic to start your work under a variety of situations, even if you navigate away from your app.

### Benefits
WorkManager is an incredibly flexible library that has many additional benefits. Some of these benefits include:
   1) Support for both asynchronous one-off and periodic tasks.
   2) Support for constraints, such as network conditions, storage space, and charging status.
   3) Chaining of complex work requests, such as running work in parallel.
   4) Output from one work request used as input for the next.
   5) Handling API-level compatibility back to API level 14 (see note).
   6) Working with or without Google Play services.
   7) Following system health best practices.
   8) Support to easily display state of work requests in the app's UI.

### When to use?
The WorkManager library is a good choice for tasks that you need to complete. The running of these tasks is not dependent on the app continuing to run after the work is enqueued. The tasks run even if the app is closed or the user returns to the home screen.
Some examples of tasks that are a good use of WorkManager:
  1) Periodically querying for latest news stories.
  2) Applying filters to an image and then saving the image.
  3) Periodically syncing local data with the network.

### Dependencies
'''kotlin
dependencies {
    // WorkManager dependency
    implementation("androidx.work:work-runtime-ktx:2.8.1")
}
kotlin```

### Work Manager Basics
  1) Worker / CoroutineWorker
     Worker is a class that performs work synchronously on a background thread. As we are interested in asynchronous work, we can use CoroutineWorker, which has interoperability with Kotlin Coroutines. You extend from the CoroutineWorker class and override the doWork() method. This method is where you put the code for the actual work you want to perform in the background.
  2) WorkRequest
     This class represents a request to do some work. A WorkRequest is where you define if the worker needs to be run once or periodically. Constraints can also be placed on the WorkRequest that require certain conditions are met before the work runs. One example is that the device is charging before starting the requested work. You pass in your CoroutineWorker as part of creating your WorkRequest.
  3) Work Manager

