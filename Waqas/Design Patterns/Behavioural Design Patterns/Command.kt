// Behavioral Design Pattern --> Behavioral design patterns are concerned with algorithms and the assignment of responsibilities between objects.
// 1) Chain of Responsibility
// 2) Command
// 3) Iterarator
// 4) Mediator
// 5) Memento
// 6) Obserever
// 7) State
// 8) Strategy
// 9) Template Method
// 10) Visitor

// Command --> Command is a behavioral design pattern that turns a request into a stand-alone object that contains all information about the request. This transformation lets you pass requests as a method arguments, delay or queue a request’s execution, and support undoable operations.

//Command Interface
interface Command{
  fun execute()
  fun undo()
}

//Receiver
class TextEditor{
  val text = StringBuilder()
  fun write(text: String){
    this.text.append(text)
  }
  fun getText():String{
    return text.toString()
  }
  fun deleteLast(){
    if(text.isNotEmpty()){
      text.deleteCharAt(text.length - 1)
    }
  }
  fun showText(){
    println(text.toString())
  }
}

//Concrete Command Class
class WriteCommand(private val editor: TextEditor, private val text: String): Command{
  private var previousText: String? = null
  override fun execute(){
    previousText = editor.getText()
    editor.write(text)
  }
  override fun undo(){
    editor.text.clear()
    editor.text.append(previousText)
  }
}

//Invoker
class History{
  private val commands = mutableListOf<Command>()
  fun push(command: Command){
    commands.add(command)
  }
  fun undo(){
    commands.removeLastOrNull()?.undo()
  }
}

fun main() {
    val editor = TextEditor()
    val writeHello = WriteCommand(editor, "Hello, ")
    val writeWorld = WriteCommand(editor, "World!")

    val history = History()

    writeHello.execute()
    editor.showText()

    writeWorld.execute()
    editor.showText()

    history.push(writeHello)
    history.push(writeWorld)

    history.undo()
    editor.showText()
}
