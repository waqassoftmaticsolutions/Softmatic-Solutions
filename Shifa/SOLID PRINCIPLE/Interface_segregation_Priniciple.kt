//Segregation: Interfaces should be specific to a particular functionality or set of functionalities that clients need. This way, clients only need to implement methods that are relevant to them.

// interface Worker{
//   fun work()
//   fun cooking()
// }

// class Programmer : Worker{
//   override fun work(){
//     println("Programming....")
//   }
// }
// //main.kt:8:1: error: class 'Programmer' is not abstract and does not implement abstract member public abstract fun cooking(): Unit defined in Worker
// //class Programmer : Worker{

// this code not folllow interface segregation principle bcz in workker we just need work function not cooking function

interface Workable{
  fun work()
}

interface Cooking{
  fun cook()
}

class Worker:Workable{
  override fun work(){
    println("Programming...")
  }
}

class Chef :Cooking{

  override fun cook(){
    println("Cheif is cooking biriyani")
  }
  
}


class Manager:Workable,Cooking{
  override fun work(){
    println("Managing...")
  }
  override fun cook(){
    println("Manager says cook to prepare lunch for meeting")
  }
}
fun main(){
  val programmer :Workable=Worker()
  programmer.work()

  val chef:Cooking=Chef()
  chef.cook()

  val manager:Manager=Manager()
  manager.work()
  manager.cook()
}
