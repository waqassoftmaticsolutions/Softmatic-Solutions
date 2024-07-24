open class Bird{
  open fun fly(){
    println("Bird is flying")
  }
}

class Sparrow:Bird(){
  override fun fly(){
    println("In this time sparrow is flying")
  }
}

class Parrot:Bird(){
  override fun fly(){
    println("Now parrot is flying")
  }
}

class Cow:Bird(){
  override fun fly(){
    println("Cow can't fly")
  }
}

fun main(){
  var bird :Bird=Sparrow();
  bird.fly();

  println("")
  bird=Cow()
  bird.fly()
}
