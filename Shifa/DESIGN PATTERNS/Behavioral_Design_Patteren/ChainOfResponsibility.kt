//The Chain of Responsibility design pattern is a behavioral pattern that allows a request 
//to be passed along a chain of handlers. Each handler in the chain processes the request or passes it to the next handler in the chain.

//Handler Interface
interface CustomerService{
  fun setNext(handler:CustomerService):CustomerService
  fun handleRequest(request:CustomerRequest)
}

//Concrete Handler
class FrontDesk:CustomerService{
  private var nextHandler:CustomerService?=null

  override fun setNext(handler:CustomerService):CustomerService{
    nextHandler=handler
    return handler
  }
  override fun handleRequest(request:CustomerRequest){
    if(request.issueType=="General Inquiry"){
      println("Front Desk Handling request : ${request.issue}")
    }else{
      nextHandler?.handleRequest(request)  //pass the request to next handler
    }
  }
}

class TechniqalSupport:CustomerService{
  private var nextHandler:CustomerService?=null
  override fun setNext(handler:CustomerService):CustomerService{
    nextHandler=handler
    return handler
  }
  override fun handleRequest(request:CustomerRequest){
    if(request.issueType=="Technical Issue"){
      println("Technical Support Handling Request : ${request.issue}")
    }else{
      nextHandler?.handleRequest(request)  //pass the request to next handler
    }
  }
}

class Specialist:CustomerService{
  private var nextHandler:CustomerService?=null
  override fun setNext(handler:CustomerService):CustomerService{
    nextHandler=handler
      return handler
  }
  override fun handleRequest(request:CustomerRequest){
     println("Specialist Handling Request : ${request.issue}")
  }
}
data class CustomerRequest(val issue:String,val issueType:String)

//client code
fun main(){
  val specialist=Specialist()
  val techsupport=TechniqalSupport()
  val frontDesk=FrontDesk()

  //configure the chain of handler
  frontDesk.setNext(techsupport).setNext(specialist)

  // Create requests
  val request1 = CustomerRequest("How to reset my password?", "General Inquiry")
  val request2 = CustomerRequest("App is crashing on login", "Technical Issue")
  val request3 = CustomerRequest("Custom integration needed", "Specialist Request")

  // Process requests
  println("Processing Request 1:")
  frontDesk.handleRequest(request1) // Handled by Front Desk

  println("\nProcessing Request 2:")
  frontDesk.handleRequest(request2) // Handled by Technical Support

  println("\nProcessing Request 3:")
  frontDesk.handleRequest(request3) // Handled by Specialist
}
