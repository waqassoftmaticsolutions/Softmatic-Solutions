interface Animals{
  fun eat()
  fun sleep()
}

interface FlyingAnimals{
  fun fly()
}

class Birds : Animals, FlyingAnimals{
  override fun eat()
  {
    println("Bird is eating")
  }
  override fun sleep()
  {
    println("Bird is sleeping")
  }
  override fun fly()
  {
    println("Bird is flying")
  }
}

class Cat:Animals{
  override fun eat()
  {
    println("Cat is eating")
  }
  override fun sleep()
  {
    println("Cat is sleeping")
  }
}

fun main()
{
  var cat = Cat()
  var bird = Birds()
  cat.eat()
  cat.sleep()

  bird.eat()
  bird.sleep()
  bird.fly()
}
