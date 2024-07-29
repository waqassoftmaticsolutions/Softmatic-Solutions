// Behavioral Design Pattern --> Behavioral design patterns are concerned with algorithms and the assignment of responsibilities between objects.
// 1) Chain of Responsibility
// 2) Command
// 3) Iterarator
// 4) Mediator
// 5) Memento
// 6) Obserever
// 7) State
// 8) Strategy
// 9) Template Method
// 10) Visitor

// State --> It is used to manage the state of an object by allowing it to change its behavior when its internal state changes

interface OrderStates{
  fun handleOrder(order:Order)
}

class Pending: OrderStates{
  override fun handleOrder(order:Order){
    println("Order is being processed")
    order.state  = Processing()
  }
}
class Processing: OrderStates{
  override fun handleOrder(order:Order){
    println("Order is being prepared for shipment")
    order.state  = Shipped()
  }
}
class Shipped: OrderStates{
  override fun handleOrder(order:Order){
    println("Order is going to be deliver")
    order.state  = Delivered()
  }
}
class Delivered: OrderStates{
  override fun handleOrder(order:Order){
    println("Order has been delivered")
  }
}

//Context
class Order(var state: OrderStates){
  fun proceed() {
      state.handleOrder(this)
  }
}
fun main() {
    val order = Order(Shipped())
    with(order){
      this.proceed() // Order is being processed.
      this.proceed() // Order is being prepared for shipment.
      this.proceed() // Order is on the way.
      this.proceed() // Order has been delivered.
    }
}
