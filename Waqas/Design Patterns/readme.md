## Creational Design Pattern

1) Singleton --> Ensure that a class has only 1 instance providing a global access points to this instance
   ## Advantages
   1) Controlled Access --> Ensures that only one instance of the class is created, providing a global point of access.
   2) Resource Management --> Can be useful for managing shared resources, such as database connections or configuration settings.
   3) Lazy Initialization --> Singleton can be instantiated lazily (on-demand), potentially improving performance and resource usage.
   ## Disadvantages
   1) Global State --> Can introduce global state into the application, which can lead to hidden dependencies and make the system harder to test.
   2) Concurrency Issues --> In a multithreaded environment, improper implementation can lead to concurrency issues if not handled correctly.
   3) Difficulty in Extending --> Can make it harder to subclass or extend, limiting flexibility in the design.
   ## When to use
   1) Configuration Management --> When you need to manage configuration settings for an application, you typically want a single source of truth. A Singleton can ensure that configuration is read once and consistently used throughout the application.
   2) Database Connections --> Managing a single connection to a database (or a connection pool) through a Singleton can help in avoiding issues related to multiple connections and can ensure that resources are used efficiently.

   
2) Builder --> Lets you to construct complex objects step by step. Allow you to produce different types and representations of an object using the same construction code
   ## Advantages
   1) Complex Object Creation: Simplifies the creation of complex objects with many optional components or configurations.
   2) Immutability: Allows the creation of immutable objects, which are safer and easier to manage.
   3) Fluent Interface: Provides a fluent interface for constructing objects, which can be more readable and user-friendly.
   ## Disadvantages
   1) Increased Number of Classes: Can introduce a large number of classes and interfaces, making the codebase more complex.
   2) Overhead: May involve additional overhead in terms of object creation if not managed properly.
   3) Not Always Necessary: Might be overkill for simple object creation scenarios where a simple constructor would suffice.
   ## When to use
   1) Useful when the object creation is very complex for example, Building a House with various rooms, windows, doors, and amenities. Instead of passing all these parameters to a single constructor, a builder allows you to set each component individually and then construct the final object.
   2) Objects with Many Optional Parameters
   ## Concept
   Product CLass --> The name of the item like (Student)
   Abstract Class --> This is Builder Class that have members same as Product class (Student Builder have some methods inherited by Concrete classes like subjects addition methods)
   Concrete Classes --> These are classes that may inherit from the abstract classes and implement the methods
   Director Class --> This will call the Concrete Classes based on the types. This may contain a student builder variable.

   
3) Prototype --> It is a creational pattern that allows you to create copies of existing objects without making your code dependent on their specific classes. It involves creating new objects by copying an existing object, known as the prototype. This pattern is particularly useful when the construction of a new object is costly or complex.
   ## Advantages
   1) Performance: Can be more efficient than creating a new instance from scratch if object creation is costly and there are many similar objects.
   2) Flexible Object Creation: Allows for the creation of objects based on existing ones, making it easier to create variations of an object.
   3) Dynamic Configuration: Can dynamically configure and clone objects at runtime.
   ## Disadvantages
   1) Complexity in Cloning: Implementing a correct and efficient cloning mechanism can be complex, especially for deep cloning of objects.
   2) Inheritance Issues: Properly implementing cloning may require careful handling of object inheritance and internal state.
   3) Dependency Management: Cloning can sometimes lead to unintended dependencies or side effects if not managed correctly.
   ## When to use
   1) When Creating a new instance of an object is resource-intensive or time-consuming.
   2) When Objects require extensive setup or initialization that makes it impractical to construct new instances directly.
   3) When You want to avoid creating multiple subclasses for different configurations or variations of an object.
   ## Concept
   Create prototype interface
   Create concrete classes tro implement the prototype
   Create registery class to get track of all concrete class

   
4) Factory
   ## Advantages
   1) 
   ## Disadvantages
   1) 
   ## When to use
   1)
5) Abstract Factory
   ## Advantages
   1) 
   ## Disadvantages
   1) 
   ## When to use
   1)

