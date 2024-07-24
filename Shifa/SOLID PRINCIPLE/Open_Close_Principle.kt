// Base interface for all pizza types
interface Pizza {
    fun send(message: String)
}

// Implementation for Crown Crust Pizza
class CrownCrustPizza(private val pizzaType: String) : Pizza {
    override fun send(message: String) {
        println("$message: Ordered successfully $pizzaType")
    }
}

// Implementation for Supreme Pizza
class SupremePizza(private val name: String) : Pizza {
    override fun send(message: String) {
        println("$message: Ordered successfully $name")
    }
}

// Function to send confirmation for any pizza type
fun confirmOrder(pizza: Pizza, message: String) {
    pizza.send(message)
}

fun main() {
    val pizzas: List<Pizza> = listOf(
        SupremePizza("Dominos Supreme Pizza"),
        CrownCrustPizza("Crown Crust Delight")
    )

    pizzas.forEach { pizza ->
        confirmOrder(pizza, "Confirmation message")
    }
}
