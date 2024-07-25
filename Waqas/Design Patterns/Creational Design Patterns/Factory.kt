// Creational Design Patterns --> Deals with object creation mechanism
// 1) Singleton
// 2) Builder
// 3) Prototype
// 4) Factory
// 5) Abstract Factory

//Factory --> It is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.

//Interface class
interface Shape{
  fun drawShape()
  fun calculateArea()
}

//Concrete CircleShape class
class CircleShape(private val radius:Double):Shape{
  override fun drawShape()
  {
    var circle:String = """

         ****
        *    *
       *      *
        *    *
         ****
    """
    println(circle)
  }
  override fun calculateArea()
  {
    println("The area of circle is ${3.14*radius*radius}")
  }
}

//Concrete RectangleShape class
class RectangleShape(private val width:Int, private val height:Int):Shape{
  override fun drawShape()
  {
    var rectangle:String = """
       **************
       *            *
       *            *
       **************
    """
    println(rectangle)
  }
  override fun calculateArea()
  {
    println("The area of circle is ${width*height}")
  }
}

//Shape Factory Class
class ShapeFactory{
  constructor()
  fun createShape(type:String):Shape?{
    if(type.lowercase() == "circle"){
      return CircleShape(2.0)
    }
    else if(type.lowercase() == "rectangle"){
      return RectangleShape(2,4)
    }
    else{
      return null
    }
  }
}

fun main() {
  var factory = ShapeFactory()
  var circle = factory.createShape("CIRCLE")
  circle?.let{
    it.drawShape()
    it.calculateArea()
  }

  var rectangle = factory.createShape("RECTANGLE")
  rectangle?.let{
    it.drawShape()
    it.calculateArea()
  }
}
