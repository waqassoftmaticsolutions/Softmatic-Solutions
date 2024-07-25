//The Prototype design pattern is a creational pattern used to create new objects by copying an existing object, known as the prototype. 
//This pattern is particularly useful when the cost of creating a new object 
//from scratch is more expensive than copying an existing one or when objects need to be created dynamically at runtime.

//Cloneable interface is a marker interface that doesn't contain any methods. It's used to indicate that a class supports cloning. 

interface EnemyPrototype {
    fun clone(): EnemyPrototype
}

data class Goblin(var health: Int, var attackPower: Int) : EnemyPrototype {
override fun clone(): EnemyPrototype {
    return copy() // Cloning using data class copy method
}

override fun toString(): String {
    return "Goblin(Health: $health, Attack Power: $attackPower)"
}
}

data class Dragon(var health: Int, var firePower: Int) : EnemyPrototype {
override fun clone(): EnemyPrototype {
    return copy()
}

override fun toString(): String {
    return "Dragon(Health: $health, Fire Power: $firePower)"
}
}

fun main() {
// Create initial enemy prototypes
val goblinPrototype = Goblin(health = 100, attackPower = 10)
val dragonPrototype = Dragon(health = 500, firePower = 50)

// Clone new enemies from prototypes
val goblinClone1 = goblinPrototype.clone() as Goblin
val goblinClone2 = goblinPrototype.clone() as Goblin
val dragonClone1 = dragonPrototype.clone() as Dragon

// Customize cloned enemies
goblinClone1.attackPower = 15
dragonClone1.health = 450

// Display original and cloned enemies
println("Original Goblin Prototype: $goblinPrototype")
println("Cloned Goblin 1: $goblinClone1")
println("Cloned Goblin 2: $goblinClone2")
println("Cloned Dragon 1: $dragonClone1")
}
