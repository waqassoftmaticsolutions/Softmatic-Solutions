

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

// Chain of Responsibility --> The Chain of Responsibility design pattern is a behavioral pattern that allows an object to pass a request along a chain of potential handlers until one of them handles the request.

//Handler Interface
interface SupportHandler{
    fun setNext(handler: SupportHandler): SupportHandler
    fun requestHandler(request: SupportRequest)
}

// Concrete Handler 1: Technical Support
class TechnicalSupport : SupportHandler {
    private var nextHandler: SupportHandler? = null
    override fun setNext(handler: SupportHandler): SupportHandler {
        nextHandler = handler
        return handler
    }
    override fun requestHandler(request: SupportRequest) {
        if (request.type == "Technical") {
            println("Technical Support handling request: ${request.details}")
        } else {
            nextHandler?.requestHandler(request)
        }
    }
}

// Concrete Handler 2: Manager
class Manager : SupportHandler {
    private var nextHandler: SupportHandler? = null
    override fun setNext(handler: SupportHandler): SupportHandler {
        nextHandler = handler
        return handler
    }
    override fun requestHandler(request: SupportRequest) {
        if (request.type == "Manager") {
            println("Manager handling request: ${request.details}")
        } else {
            nextHandler?.requestHandler(request)
        }
    }
}

// Concrete Handler 3: Director
class Director : SupportHandler {
    private var nextHandler: SupportHandler? = null
    override fun setNext(handler: SupportHandler): SupportHandler {
        nextHandler = handler
        return handler
    }
    override fun requestHandler(request: SupportRequest) {
        if (request.type == "Director") {
            println("Director handling request: ${request.details}")
        } else {
            nextHandler?.requestHandler(request)
        }
    }
}

// Request class
data class SupportRequest(val type: String, val details: String)

fun main() {
    val techSupport = TechnicalSupport()
    val manager = Manager()
    val director = Director()

    techSupport.setNext(manager).setNext(director)

    val techRequest = SupportRequest("Technical", "Computer not working")
    val managerRequest = SupportRequest("Manager", "Request for additional resources")
    val directorRequest = SupportRequest("Director", "Budget approval needed")

    techSupport.requestHandler(techRequest)
    techSupport.requestHandler(managerRequest)
    techSupport.requestHandler(directorRequest)
}

