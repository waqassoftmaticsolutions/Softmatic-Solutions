//The Mediator design pattern is a behavioral pattern that defines an object, known as the mediator, to encapsulate how a set of objects interact. The mediator promotes loose coupling by preventing objects 
//from referring to each other explicitly, allowing their interactions to be varied independently.

// Mediator Interface
interface ChatRoomMediator {
    fun showMessage(user: User, msg: String)
}

// Concrete Mediator
class ChatRoom : ChatRoomMediator {
    override fun showMessage(user: User, msg: String) {
        println("${user.name}: $msg")
    }
}

// Colleague Interface
abstract class User(protected val mediator: ChatRoomMediator, val name: String) {
    abstract fun send(msg: String)
}

// Concrete Colleague
class ChatUser(mediator: ChatRoomMediator, name: String) : User(mediator, name) {
    override fun send(msg: String) {
        mediator.showMessage(this, msg) 
    }
}

// Client code
fun main() {
    val chatRoom = ChatRoom()

    val user1 = ChatUser(chatRoom, "Alice")
    val user2 = ChatUser(chatRoom, "Bob")

    user1.send("Hi, Bob!")
    user2.send("Hello, Alice!")
}
