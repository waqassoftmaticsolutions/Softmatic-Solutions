## Dependency Injection
It is a process of supplying a resource that a given piece of code requires. The required resource, which is often a component of the application itself is called a dependency.
Dependency injection is used for managing dependencies in a software system to achieve better modularity, flexibility and testability.
Dependency --> A dependency is any object that another object relies on to function properly.
```kotlin
class UserService(private val userRepository: UserRepository){
  fun User getUserById(id: Int){
    return userRepository.findById(id)
  } 
}
```
Here in the aqbove example UserRepository is dependency of UserService because UserService needs UserRepository to retrieve user data.

Injection --> Injection refers to the process of providing dependencies to an object from an external source rather than creating them within the object. This can be done through various methods such as constructors, setters, or interfaces.
# Types of Injectiontion
  ### 1) Constructor Injection --> Dependencies are added through class constructor.
```kotlin
     // User.kt
      data class User(val id: Int, val name: String)
      // UserRepository.kt
      class UserRepository {
          fun findUserById(id: Int): User {
              // Logic to find a user by id
              return User(id, "John Doe")
          }
      }
      // UserService.kt
      class UserService(private val userRepository: UserRepository) {
          fun getUserById(id: Int): User {
              return userRepository.findUserById(id)
          }
      }
      // Main.kt
      fun main() {
          val userRepository = UserRepository()
          val userService = UserService(userRepository)
          val user = userService.getUserById(1)
          println(user.name)  // Output: John Doe
      }
```
  
 ### 2) Setter Injection --> Dependencies are added through class setter methods.
```kotlin
// User.kt
    data class User(val id: Int, val name: String)
    // UserRepository.kt
    class UserRepository {
        fun findUserById(id: Int): User {
            // Logic to find a user by id
            return User(id, "John Doe")
        }
    }
    // UserService.kt
    class UserService {
        private lateinit var userRepository: UserRepository
        fun setUserRepository(userRepository: UserRepository) {
            this.userRepository = userRepository
        }
        fun getUserById(id: Int): User {
            return userRepository.findUserById(id)
        }
    }
    // Main.kt
    fun main() {
        val userRepository = UserRepository()
        val userService = UserService()
        userService.setUserRepository(userRepository)
        val user = userService.getUserById(1)
        println(user.name)  // Output: John Doe
    }
```
 ### 3) Interface Injection --> Dependencies are added through an interface.
```kotlin
     // User.kt
    data class User(val id: Int, val name: String)
    
    // UserRepository.kt
    class UserRepository {
        fun findUserById(id: Int): User {
            // Logic to find a user by id
            return User(id, "John Doe")
        }
    }
    
    // UserRepositoryInjector.kt
    interface UserRepositoryInjector {
        fun injectUserRepository(userRepository: UserRepository)
    }
    
    // UserService.kt
    class UserService : UserRepositoryInjector {
        private lateinit var userRepository: UserRepository
    
        override fun injectUserRepository(userRepository: UserRepository) {
            this.userRepository = userRepository
        }
    
        fun getUserById(id: Int): User {
            return userRepository.findUserById(id)
        }
    }
    // Main.kt
    fun main() {
        val userRepository = UserRepository()
        val userService = UserService()
    
        userService.injectUserRepository(userRepository)
    
        val user = userService.getUserById(1)
        println(user.name)  // Output: John Doe
    }
```
## Advantages
   1) Decoupling Components --> It means that classes don't need to know about concrete implementations of their dependencies, only the interfaces.
   2) Loose Coupling --> Components are less dependent on their specific implementations of their dependencies
   3) Improve Code Quality --> By promoting single responsibility principle (SRP) and separation of concerns (SoC), DI leads to cleaner and more maintainable code.
   4) Easier Testing
   5) Flexibility --> Easier to swap different implementations of dependencies without changing the dependent class.
## Disadvantages
  1) Increased Complexity: DI can add complexity to the codebase. Understanding and managing the dependency injection framework can be challenging, especially for beginners or in large projects.
  2) Performance Impact: There can be a performance overhead associated with DI, especially in the initialization phase, due to the additional layer of abstraction and reflection used by many DI frameworks.
  3) Runtime Errors: Many DI-related issues (like missing dependencies or circular dependencies) can only be detected at runtime rather than compile-time, potentially leading to runtime errors that are harder to catch early in the development process.
