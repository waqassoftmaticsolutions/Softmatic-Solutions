## Creational Design Pattern

## 1) Singleton 
   Ensure that a class has only 1 instance providing a global access points to this instance
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

   
## 2) Builder
   Lets you to construct complex objects step by step. Allow you to produce different types and representations of an object using the same construction code
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
   i) Product CLass --> The name of the item like (Student)
   ii) Abstract Class --> This is Builder Class that have members same as Product class (Student Builder have some methods inherited by Concrete classes like subjects addition methods)
   iii) Concrete Classes --> These are classes that may inherit from the abstract classes and implement the methods
   iv) Director Class --> This will call the Concrete Classes based on the types. This may contain a student builder variable.

   
## 3) Prototype
   It is a creational pattern that allows you to create copies of existing objects without making your code dependent on their specific classes. It involves creating new objects by copying an existing object, known as the prototype. This pattern is particularly useful when the construction of a new object is costly or complex.
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
   i) Create prototype interface
   ii) Create concrete classes tro implement the prototype
   iii) Create registery class to get track of all concrete class

   
## 4) Factory 
It is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created. We create object without exposing the creation logic to the client and refer to newly created object using a common interface.
   ## Advantages
   1) Encapsulation: Encapsulates the creation logic of objects, which can help in managing and controlling the object creation process.
   2) Flexibility: Provides a way to create objects without specifying the exact class of object that will be created.
   3) Extension: Makes it easier to extend and modify object creation logic by adding new subclasses or factories.
   ## Disadvantages
   1) Complexity: Can introduce additional complexity by adding multiple factory methods or classes.
   2) Overhead: Increases the number of classes in the system, potentially leading to more complex code management.
   3) Dependency Injection: If not designed properly, it may lead to tight coupling between the creator and the concrete classes.
   ## When to use
   1) The process of creating an object involves complex logic or multiple steps that should be encapsulated away from the client code. For example, Creating different types of Document objects (e.g., Report, Invoice, Letter) might involve complex setup that can be managed by a factory, rather than having the client handle the specifics.
   ## Concept
   i) Create interface (Shape)
   ii) Create concrete classes that implements the interface (like cirecle implements the shape)
   iii) Create Factory class that will call the function based on the type of concrete class
   
      
## 5) Abstract Factory
It is a creational design pattern that lets you produce families of related objects without specifying their concrete classes.
   ## Advantages
   1) Consistency: Ensures that related objects are created in a consistent manner by encapsulating families of related products.
   2) Flexibility: Makes it easy to switch between different product families without altering the code that uses them.
   3) Encapsulation: Encapsulates the creation of related objects, which can help in managing complex object creation scenarios.
   ## Disadvantages
   1) Complexity: Can add significant complexity to the codebase due to the number of interfaces and concrete factories involved.
   2) Overhead: May involve additional overhead in terms of design and maintenance of the abstract factory classes and interfaces.
   3) Difficulty in Extending: Adding new product variants may require modifying existing abstract factories and implementations, which can impact existing code.
   ## When to use
   1) When You need to create multiple related objects that are designed to work together. Each family of objects might be a different variant or style but still needs to adhere to a common interface. For example, In a UI framework, you might have different themes like DarkTheme and LightTheme. Each theme consists of a family of related objects, such as Button, TextField, and Scrollbar. The Abstract Factory can provide the appropriate set of UI components for the selected theme.
   ## Concept
   i) Interface like Shape
   ii) Concrete Classes that will implement Interface like circular rectangle
   iii) Abstract Factory Class
   iv) Concrete Class that inherits the abstract factory that is used to call the specific class.
