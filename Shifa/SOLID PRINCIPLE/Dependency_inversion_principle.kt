//Abstraction
interface NotificationService{
  fun sendNotification(recipient:String,message:String)
}


//Low level modules
class EmailService:NotificationService{
  override fun sendNotification(recipient:String,message:String){
    println("sending email to $recipient : $message")
  }
}

//High level modules
class ReportGenerator(val notification:NotificationService){
  fun generateReport(recepient:String){
    notification.sendNotification(recepient, "Report Generated")
  }
}


fun  main(){
  val emailService: NotificationService = EmailService()
  val reportGenerator = ReportGenerator(emailService)
  reportGenerator.generateReport("shifa@gmail.com")
}
