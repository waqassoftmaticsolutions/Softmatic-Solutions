// Creational Design Patterns --> Deals with object creation mechanism
// 1) Singleton
// 2) Builder
// 3) Prototype
// 4) Factory
// 5) Abstract Factory

//Prototype --> It is a creational pattern that allows you to create copies of existing objects without making your code dependent on their specific classes. It involves creating new objects by copying an existing object, known as the prototype. This pattern is particularly useful when the construction of a new object is costly or complex.

//Steps
//Create prototype interface
//Create concrete classes tro implement the prototypw
//Create registery class to get track of all concrete class

interface Prototype{
  fun clone():Prototype
}

class Circle(private val radius: Double) : Prototype {
  constructor(circle: Circle) : this(circle.radius)
  override fun clone(): Prototype {
    return Circle(radius)
  }
  fun getInfo(): String {
      return "Circle with radius $radius"
  }
}

class Rectangle(private val height:Int, private  val width:Int): Prototype{
  constructor(rectangle: Rectangle) : this(rectangle.width, rectangle.height)
  override fun clone(): Prototype {
    return Rectangle(height, width)
  }
  fun getInfo(): String {
      return "Rectangle of width $width and height $height"
  }
}

// Prototype registry
class PrototypeRegistry {
    private val prototypes = mutableMapOf<String, Prototype>()

    fun registerPrototype(key: String, prototype: Prototype) {
        prototypes[key] = prototype
    }

    fun unregisterPrototype(key: String) {
        prototypes.remove(key)
    }

    fun getPrototype(key: String): Prototype? {
        return prototypes[key]?.clone()
    }
}

fun main() {
    val circlePrototype = Circle(10.4)
    val rectanglePrototype = Rectangle(5, 7)

    val registry = PrototypeRegistry()
    registry.registerPrototype("circle", circlePrototype)
    registry.registerPrototype("rectangle", rectanglePrototype)

    val clonedCircle = registry.getPrototype("circle") as Circle?
    val clonedRectangle = registry.getPrototype("rectangle") as Rectangle?

    clonedCircle?.let { 
      println(it.getInfo()) 
    }  
    clonedRectangle?.let { 
      println(it.getInfo()) 
    }  
}
