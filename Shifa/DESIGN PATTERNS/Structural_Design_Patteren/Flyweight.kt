//used to minimize memory usage by sharing as much data as possible with similar objects. 
//It is especially useful when you have a large number of objects that share common data. The pattern helps reduce memory consumption by storing shared data 


import java.util.HashMap

//flyweight interface
interface Icon{
  fun draw(x:Int,y:Int)
}

//concrete flyweight class
class FileIcon(private val type:String,private val imageName:String):Icon{
  override fun draw(x:Int,y:Int){
    println("Drawing $type icon with image $imageName at position ($x , $y)")
  }
}
//Concrete flyweight class 2
class FolderIcon(private val color:String,private val imageName:String):Icon{
  override fun draw(x:Int,y:Int){
    println("Drawing folder icon with color $color and image $imageName at position ($x, $y)")
  }
}

//Flyweight Factory to manage creation and retrieval of flyweight object
class IconFactory{
  private val iconCache=HashMap<String, Icon>()
  fun getIcon(key:String):Icon{
    return iconCache.getOrPut(key) { 
      when(key){
        "file"->FileIcon("document", "document.png")
        "folder"->FolderIcon("Red", "folder.png")
        else->throw IllegalArgumentException("Unsupported icom type: $key")
      }
    }
  }
}


//main
fun main(){
  val iconFactory=IconFactory()
  //Draw file icons at different positions
  val fileIcon1=iconFactory.getIcon("file")
  fileIcon1.draw(100,250)

  fileIcon1.draw(50,200)
  val fileIcon2=iconFactory.getIcon("folder")
  fileIcon2.draw(40,40)
}
