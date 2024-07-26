// Structural design patterns in software engineering focus on how classes and objects are composed to form larger structures while maintaining flexibility and efficiency. These patterns typically deal with relationships between objects, simplifying the design and making the system more manageable.
// 1) Adaptor
// 2) Bridge
// 3) Composite
// 4) Decorator
// 5) Facade
// 6) Flyweight
// 7) Proxy

//Flyweight --> Flyweight pattern is primarily used to reduce the number of objects created and to decrease memory footprint and increase performance. This type of design pattern comes under structural pattern as this pattern provides ways to decrease object count thus improving the object structure of application.

//Flyweight interface
interface Font{
  fun applyFont(text: String):String
}

//Concrete Flyweight that implements the Flyweight interface
class FontStyle(private val style: String):Font{
  override fun applyFont(text: String):String{
    return "Style --> [$style] and Text --> [$text]"
  }
}

//Flyweight Factory that will manage the reuse of the object
class FontFactory{
  
  companion object {
    private val fonts = mutableMapOf<String, Font>()
    fun getFont(style: String):Font{
      if(!fonts.containsKey(style)){
        fonts[style] = FontStyle(style)
      }
      return fonts[style]!!
    }
  }
}
// Client Code
fun main() {
    val font1 = FontFactory.getFont("Underlined")
    val font2 = FontFactory.getFont("Italic")
    val font3 = FontFactory.getFont("Bold") 
    val font4 = FontFactory.getFont("Underlined") 

    println(font1.applyFont("Waqas"))
    println(font2.applyFont("Ehtisham")) 
    println(font3.applyFont("Umar"))
    println(font4.applyFont("Qadeer"))
    println(font1 === font3)
    println(font1 === font4)
    println(font1 === font2)
}
