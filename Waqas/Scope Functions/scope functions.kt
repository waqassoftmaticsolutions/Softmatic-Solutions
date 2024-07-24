// Scope Functions
// These are used to make the code readable

/*Use of with scope function
Context --> this
Return value --> lamda result
This is used when you have to perform multiple operations on object
Without modifying the object itself.*/

class Person {
  var name = "Waqas"
  var id = 8
}

class User {
  var name: String? = null
  var id: Int? = null
}

fun main() {
  // Testing of with scope function
  // Without scope function
  println("------------------use of with scope function-------------")
  var person = Person()
  println(person.name)
  println(person.id)

  // With use of scope function
  val person1 = Person()
  val id1: Int =
      with(person1) {
        println(name)
        println(id)
        id + 1
      }
  println("Id is: ${id1}")
  with(person1) {
    println(name)
    println(id)
  }

  // Testing the apply scope function
  // Without using apply function
  println("------------------use of apply scope function-------------")
  var user = User()
  user.name = "M Waqas"
  user.id = 8
  println(user.name)
  println(user.id)
  //With use of apply function
  val user1 = User().apply{
    name = "Waqas"
    id = 9
  }
  with(user1) {
    println(name)
    println(id)
  }

  // Testing the also function
  println("------------------use of also scope function-------------")
  val numberList: MutableList<Int> = mutableListOf(1, 2, 3)
  val duplicateList =
      numberList.also {
        println("elements are : $it")
        it.add(4)
        println("elements are : $it")
        it.remove(1)
        println("elements are : $it")
      }
  println("original list : $numberList")
  println("duplicate list : $duplicateList")

  // Testing the let function
  println("------------------use of let scope function-------------")
  val name: String? = "waqas"
  // println(name!!.reversed()) //throws an exception
  // println(name.length)

  // We can use let scope function to avoid not   null pointer exception
  name?.let {
    println(name.reversed())
    println(name.length)
  }

   // Testing the run  function
   println("------------------use of run scope function-------------")
  val person4: Person? = Person()
  val temp = person4?.run{
    println(person4.name)
    println(person4.id)
    "waqas"
  }
  println(temp)
}
