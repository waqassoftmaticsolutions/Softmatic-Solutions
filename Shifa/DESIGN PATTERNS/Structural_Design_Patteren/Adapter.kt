//The Adapter design pattern is a structural design pattern that allows objects with incompatible interfaces to work together. 
//It acts as a bridge between two interfaces, allowing them to collaborate without modifying their source code

//"legacy" often refers to code that is already written and used in a system but might not follow current design practices 

//Target Interface
interface Shape{
  fun draw()
}
//Adaptee
class LegacacyRectangle{
  fun drawRectangle(){
    println("Drawing rectangle using legacy code")
      println("******************") // Print the top row
      for (i in 1..5) { // Loop to print middle rows
        println("*                *") // Print a row with asterisks on the sides
      }
      println("******************") // Print the bottom row
  }
}
//Adapter
class RectangleAdapter(val legacyrec:LegacacyRectangle):Shape{
  override fun draw(){
    legacyrec.drawRectangle()
  }
}
//Client
class DrawingApplication{
  val shapes:MutableList<Shape> =mutableListOf()

  fun addShape(shape: Shape) {
    shapes.add(shape) 
  }

  fun drawShapes(){
    shapes.forEach{it.draw()}
  }
}

fun main(){
  val drawingApp=DrawingApplication()
  val legacyRectangle=LegacacyRectangle()
  //drawingApp.addShape(legacyRectangle)
  //Type mismatch: inferred type is LegacacyRectangle but Shape was expected (kotlin)
  //got this error if not use adapter
  val rectangleAdapter=RectangleAdapter(legacyRectangle)
  drawingApp.addShape(rectangleAdapter)
  drawingApp.drawShapes()
  
}
