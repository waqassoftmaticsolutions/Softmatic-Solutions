// Creational Design Patterns --> Deals with object creation mechanism
// 1) Singleton
// 2) Builder
// 3) Prototype
// 4) Factory
// 5) Abstract Factory


//Abstract Factory --> It is a creational design pattern that lets you produce families of related objects without specifying their concrete classes.
interface ShapeAF{
    fun draw()
}

//Rounded Family
class RoundedRectangle:ShapeAF{
    override fun draw(){
        println("Priniting Rounded Rectangle")
    }
}
class RoundedSquare:ShapeAF{
    override fun draw(){
        println("Priniting Rounded Square")
    }
}

//Simple Family
class RectangleAF:ShapeAF{
    override fun draw(){
        println("Priniting Rectangle")
    }
}
class SquareAF:ShapeAF{
    override fun draw(){
        println("Printing Square")
    }
}

//Abstract Factory class
abstract class AbstractFactory {
    constructor()
   abstract fun getShape(shapeType: String) :ShapeAF?
}

//Factory classes
class SimpleShapeFactory:AbstractFactory() { 
    override fun getShape(shapeType: String): ShapeAF? {
        when (shapeType) {
            "RECTANGLE" -> return RectangleAF()
            "SQUARE" -> return SquareAF()
            else -> return null
        }
    }
}

class RoundedShapeFactory:AbstractFactory() { 
    override fun getShape(shapeType: String): ShapeAF? {
        when (shapeType) {
            "RECTANGLE" -> return RoundedRectangle()
            "SQUARE" -> return RoundedSquare()
            else -> return null
        }
    }
}

//Factory Producer
class FactoryProducer{
    fun getObject(type: String):AbstractFactory?{
        if(type == "rounded"){
            return RoundedShapeFactory()
        }
        else if(type == "simple"){
            return SimpleShapeFactory()
        }
        else{
            return null
        }
    }
}

fun main() {
    //Abstract Factory
  val factoryProducer = FactoryProducer()
  val roundedShapeFactory = factoryProducer.getObject("rounded")
  val simpleShapeFactory = factoryProducer.getObject("simple")

  // Rounded Shapes
  val roundedRectangle = roundedShapeFactory?.getShape("RECTANGLE")
  roundedRectangle?.draw()
  val roundedSquare = roundedShapeFactory?.getShape("SQUARE")
  roundedSquare?.draw()

  // Simple Shapes
  val rectangle = simpleShapeFactory?.getShape("RECTANGLE")
  rectangle?.draw()
  val square = simpleShapeFactory?.getShape("SQUARE")
  square?.draw()
}
