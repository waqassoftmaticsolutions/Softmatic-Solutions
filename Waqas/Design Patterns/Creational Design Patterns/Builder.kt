// Creational Design Patterns --> Deals with object creation mechanism
// 1) Singleton
// 2) Builder
// 3) Prototype
// 4) Factory
// 5) Abstract Factory

// Builder --> It is a creational design pattern that lets you construct complex objects step by
// step. The pattern allows you to produce different types and representations of an object using
// the same construction code.

// Proiduct
class Student {
  var rollNum: Int? = null
  var firstName: String? = null
  var secondName: String? = null
  var age: Int? = null
  var subjects: List<String> = mutableListOf()
  constructor(builder: StudentBuilder?) {
    rollNum = builder!!.rollNum
    firstName = builder.firstName
    secondName = builder.secondName
    age = builder.age
    subjects = builder.subjects
  }
  fun printData() {
    println("Roll Number: ${rollNum ?: "Not provided"}")
    println("First Name: ${firstName ?: "Not provided"}")
    println("Second Name: ${secondName ?: "Not provided"}")
    println("Age: ${age ?: "Not provided"}")
    println(
        "Subjects: ${subjects.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: "Not provided"}"
    )
  }
}

// Abstract class
abstract class StudentBuilder {
  var rollNum: Int? = null
  var firstName: String? = null
  var secondName: String? = null
  var age: Int? = null
  var subjects: List<String> = mutableListOf()

  constructor()
  fun setFirstName(name: String): StudentBuilder {
    this.firstName = name
    return this
  }
  fun setSedcondName(name: String): StudentBuilder {
    this.secondName = name
    return this
  }
  fun setRollNum(roll: Int): StudentBuilder {
    this.rollNum = roll
    return this
  }
  fun setAge(age1: Int): StudentBuilder {
    this.age = age1
    return this
  }
  fun builder(): StudentBuilder {
    return this
  }
  // This function is marked as 'open' in the abstract class, but it is not defined in the abstract
  // class.
  // It needs to be implemented in the derived classes.
  open fun setSubjects(): StudentBuilder {
    return this
  }
}

// Concrete class 1
class EngineeringStudent : StudentBuilder() {
  // Implement the setSubjects() function for Engineering students.
  override fun setSubjects(): StudentBuilder {
    var subject: List<String> = mutableListOf("Physics", "Chemistry", "Math")
    this.subjects = subject
    return this
  }
}
// Concrete class 2
class ComputerStudent : StudentBuilder() {
  // Implement the setSubjects() function for Computer students.
  override fun setSubjects(): StudentBuilder {
    var subject: List<String> = mutableListOf("OOP", "DSA", "DB")
    this.subjects = subject
    return this
  }
}

// Director class
class Director {
  var studentBuilder: StudentBuilder? = null

  fun createStudent(): Student {
    // Use the existing studentBuilder property.
    return Student(studentBuilder!!.builder())
  }
}

fun main() {
  val director = Director()

  // Create an EngineeringStudent
  director.studentBuilder =
      EngineeringStudent()
          .setFirstName("John")
          .setSedcondName("Doe")
          .setRollNum(101)
          .setAge(20)
          .setSubjects()
  val engineeringStudent = director.createStudent()
  engineeringStudent.printData()

  println("\n---\n")

  // Create a ComputerStudent
  director.studentBuilder =
      ComputerStudent()
          .setFirstName("Jane")
          .setSedcondName("Smith")
          .setRollNum(102)
          .setAge(21)
          // Call the implemented setSubjects() function in the ComputerStudent class.
          .setSubjects()

  val computerStudent = director.createStudent()
  computerStudent.printData()
}
