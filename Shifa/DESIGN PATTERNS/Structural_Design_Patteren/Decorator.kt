//Decorator is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.



//Component
interface Notifier{
  fun send(msg:String)
}

//Concrete component
class BaseNotifier:Notifier{
  override fun send(msg:String){
    println("Sending Basic Notifications : $msg")
  }
}

//Decorator
abstract class NotifierDecorator(val notifier:Notifier):Notifier{
  override fun send(msg:String){
    notifier.send(msg)
  }
}

//Concrete Decorator 1
class EmailNotifier(notifier:Notifier):NotifierDecorator(notifier){
  override fun send(msg:String){
    super.send(msg)
    sendEmail(msg)
  }

  private fun sendEmail(msg:String){
    println("Sending email Notifications : $msg")
  }
}

//concrete Decorator 2
class SMSNotifier(notifier:Notifier):NotifierDecorator(notifier){
  override fun send(msg:String){
    super.send(msg)
    sendSMS(msg)
  }
  private fun sendSMS(msg:String){
    println("Sending SMS Notifications : $msg")
  }
}

//main
fun main(){
  var notifier:Notifier=BaseNotifier();
  notifier.send("Hello world")
  notifier=EmailNotifier(notifier)
  notifier.send("Email sent")
}
