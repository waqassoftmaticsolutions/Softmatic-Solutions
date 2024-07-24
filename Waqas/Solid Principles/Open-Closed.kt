//Open-Close Principle
interface Shape{
  fun drawShape()
}

class Circle:Shape{
  override fun drawShape(){
    var circle:String = """

         ****
        *    *
       *      *
        *    *
         ****
    """
    println(circle)
  }
}

class Renctangle:Shape{
  override fun drawShape(){
    var rectangle:String = """
       **************
       *            *
       *            *
       **************
    """
    println(rectangle)
  }
}
class Triangle:Shape{
  override fun drawShape(){
    var triangle:String = """
                *
              *   *
            *       *
          *           *
        *               *
        *****************
    """
    println(triangle)
  }
}
fun main(){
  var circle = Circle()
  circle.drawShape()
  var rectangle = Renctangle()
  rectangle.drawShape()
  var triangle = Triangle()
  triangle.drawShape()
}
