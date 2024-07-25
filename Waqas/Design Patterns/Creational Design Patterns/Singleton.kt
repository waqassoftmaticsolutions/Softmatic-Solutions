//Creational Design Patterns --> Deals with object creation mechanism
//1) Singleton 
//2) Builder
//3) Prototype
//4) Factory
//5) Abstract Factory

//Singleton --> It ensures that a class has only one instance accesing a global access to that instance
//Compamnion object --> a way to define properties and methods that are associated with the class itself rather than with instances of the class.

class Singleton {
  private constructor(){}
  init {
    println("You are creating an instance of the object")
  }
  companion object{
    private var singleton: Singleton? = null
    fun getInstance(): Singleton{
      if(singleton == null){
        println("Instance has been created successfully")
        singleton = Singleton()
      }else{
        println("Instance already exists")
      }
      return singleton!!
    }
  }
}

fun main(){
  //Singleton Design Pattern
  var singleton1 = Singleton.getInstance()
  var singleton2 = Singleton.getInstance()
  if(singleton1 === singleton2){
    println("Both are same")
  }
}
