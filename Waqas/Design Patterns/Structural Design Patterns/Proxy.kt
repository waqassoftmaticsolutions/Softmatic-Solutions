// Structural design patterns in software engineering focus on how classes and objects are composed to form larger structures while maintaining flexibility and efficiency. These patterns typically deal with relationships between objects, simplifying the design and making the system more manageable.
// 1) Adaptor
// 2) Bridge
// 3) Composite
// 4) Decorator
// 5) Facade
// 6) Flyweight
// 7) Proxy

//Proxy --> Proxy is a structural design pattern that lets you provide a substitute or placeholder for another object. A proxy controls access to the original object, allowing you to perform something either before or after the request gets through to the original object.

interface Image{
  fun display()
}

class RealImage(private val fileName: String):Image{
  
  init {
    loadFromDisk()
  }
  
  private fun loadFromDisk() {
    println("Loading $fileName")
  }
  override fun display() {
      println("Displaying $fileName")
  }
}

// Proxy: ProxyImage
class ProxyImage(private val fileName: String) : Image {
    private var realImage: RealImage? = null
    override fun display() {
        if (realImage == null) {
            realImage = RealImage(fileName)
        }
        realImage?.display()
    }
}
fun main() {
    val image1: Image = ProxyImage("waqas.jpg")
    val image2: Image = ProxyImage("ehtisham.jpg")

    image1.display() 
    image1.display()
    image2.display()  
}
