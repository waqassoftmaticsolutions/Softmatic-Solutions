//SCOPE FUNCTIONS MAKES OUR CODE MORE CONCISE AND READABLE

//USAGE OF "WITH FUNCTION"
class Person{
  var name="Shifa"
  var address="Softmatic solution"
}


//USAGE OF "APPLY FUNCTION"
class Person2{
  var name=""
  var address=""
}

fun main() {
  
  // // 1---------------"WITH"
  // //Without using scope function
  // var person =Person()
  // println("${person.name} works in ${person.address}" )


  // //With the help of scope "with function"
  // var fullname : String=with(person){
  //   println("${name} works in ${address}")
  //   name + "Fatima"
  // }
  // println("Full name is ${fullname} ")


  // 2-------------"APPLY"
  // //without using scope function
  // var person2=Person2()
  // person2.name="shifa fatima"
  // person2.address="softmatic"
  // println("${person2.name} works in ${person2.address}" )

  // //with the use of scope "apply" function
  // var p=Person2().apply{
  //   name="shifa"
  //   address="softmatic"
  // }
  // with(p){
  //   println("${name} works in ${address}")
  // }

  
  // //3 --------------"ALSO"
  // //without using scope function
  // var list1: MutableList<Int> =mutableListOf(1,2,3)
  // list1.add(4)
  // list1.remove(1)
  // println(list1)

  // //with the help of scope function
  // list1.also{
  //   it.add(1)
  //   it.add(5)
  // }
  // println(list1)

  
// //4 ----------------"LET"
//   //without using scope function
//   val name: String? = null
//   //println(name!!.reversed())  //this code will through : Exception in thread "main" java.lang.NullPointerException

//   //to prevent this see below code where with the help of safe call operator avoid this exception [?.]-->safe call operator
//   //with the help of scope function
//   name?.let{
//     it.reversed()
//     println("name is ${it}")
//   }


// //5 -----------------"Run"
  // //without using scope function
  // var p3: Person?=null
  // with(p3){
  //     println("${name} works in ${address}")
  //     name + "Fatima"
  //   }   //when you run this code you get :main.kt:79:18: error: only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type Person?
  
  //  //To prevent this we use run scope function

  
  // var fullname : String?=p3?.run{
  //     println("${name} works in ${address}")
  //     name + "Fatima"
  //   }
  // println(fullname)

}
