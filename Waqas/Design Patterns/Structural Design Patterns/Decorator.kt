// Structural design patterns in software engineering focus on how classes and objects are composed to form larger structures while maintaining flexibility and efficiency. These patterns typically deal with relationships between objects, simplifying the design and making the system more manageable.
// 1) Adaptor
// 2) Bridge
// 3) Composite
// 4) Decorator
// 5) Facade
// 6) Flyweight
// 7)Proxy

//Decorator --> Allow you to dynamically add behaviors or responsibility to objects without altering their structure

//Component --> Coffee
interface Coffee{
  fun cost():Double
}

//ConcreteComponent 
class NormalCoffee:Coffee{
  override fun cost():Double{
    return 50.0
  }
}

//Decorator
abstract class Decorator(private val coffee:Coffee):Coffee{
  override fun cost():Double{
    return coffee.cost()
  }
}

//Concrete Decorator
class CoffeeWithMilk(private val coffee:Coffee):Decorator(coffee){
  override fun cost():Double{
    return super.cost() + 30.0
  }
}
class CoffeeWithSugar(private val coffee:Coffee):Decorator(coffee){
  override fun cost():Double{
    return super.cost() + 10.0
  }
}
class CoffeeWithSugarMilk(private val coffee:Coffee):Decorator(coffee){
  override fun cost():Double{
    return super.cost() + 35.0
  }
}

fun main(){
  var normalCoffee:Coffee = NormalCoffee()
  with(normalCoffee){
    println("Cost of Normal Coffee is : ${this.cost()}")
  }

  var milkCoffee:Coffee = CoffeeWithMilk(normalCoffee)
  with(milkCoffee){
    println("Cost of Milk Coffee is : ${this.cost()}")
  }

  var sugarCoffee:Coffee = CoffeeWithSugar(normalCoffee)
  with(sugarCoffee){
    println("Cost of Sugar Coffee is : ${this.cost()}")
  }

  var sugarMilkCoffee:Coffee = CoffeeWithSugarMilk(normalCoffee)
  with(sugarMilkCoffee){
    println("Cost of Sugar and Milk Coffee is : ${this.cost()}")
  }
}
