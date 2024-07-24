interface Sender{
  fun sendNotifications(message: String)
}
//Low level modules
class EmailNotificationSender : Sender{
  override fun sendNotifications(message: String){
    println("We are sending ${(message)} by Email")
  }
}
class MessageNotificationSender : Sender{
  override fun sendNotifications(message: String){
    println("We are sending ${(message)} by Message")
  }
}
//High level module
class Notifications(private val sender: Sender){
  fun sendNotifications(message: String){
    sender.sendNotifications(message)
  }
}

fun main()
{
  val email = EmailNotificationSender()
  val message = MessageNotificationSender()

  val notificationEmail = Notifications(email)
  notificationEmail.sendNotifications("Hello waqas how are you?")

  val notificationMessage = Notifications(message)
  notificationMessage.sendNotifications("Hello waqas how are you?")
}
