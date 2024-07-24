//The Singleton Design Pattern ensures that a class has only one instance and provides a global point of access to that instance. 
//It is commonly used for managing global states, shared resources, or configurations where only one instance is needed throughout the application's lifecycle.

class Singleton private constructor() {
  companion object {
      // Single instance of Singleton
      private var instance: Singleton? = null

      // Method to access the instance
      fun getInstance(): Singleton {
          if (instance == null) {
              instance = Singleton()
          }
          return instance!!
      }
  }

  fun doSomething() {
      println("Singleton instance doing something.")
  }
}

fun main() {
  
  val singletonInstance = Singleton.getInstance()
  singletonInstance.doSomething() // Output: Singleton instance doing something.
}
