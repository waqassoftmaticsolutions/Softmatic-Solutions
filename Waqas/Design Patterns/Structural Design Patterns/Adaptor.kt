// Structural design patterns in software engineering focus on how classes and objects are composed to form larger structures while maintaining flexibility and efficiency. These patterns typically deal with relationships between objects, simplifying the design and making the system more manageable.
// 1) Adaptor
// 2) Bridge
// 3) Composite
// 4) Decorator
// 5) Facade
// 6) Flyweight
// 7)Proxy

// Adaptor --> It work as a bridge between two incompatible and independent interfaces. It also translates one interface to another. Using itconnect a new version of component to an older version of the system without modifying thew existing system.

// Third-party service class with incompatible interface
class ThirdPartyService {
    fun requestData(): String {
        return "Data from third-party service that is incompatible with the interface"
    }
}
// Client interface
interface ClientInterface {
    fun fetchData(): String
}
// Adapter class
class ServiceAdapter(private val thirdPartyService: ThirdPartyService) : ClientInterface {
    override fun fetchData(): String {
        // Delegate the call to the third-party service object
        return thirdPartyService.requestData()
    }
}
// Client class using the adapter via the client interface
class Client(private val clientInterface: ClientInterface) {
    fun useService(): String {
        return clientInterface.fetchData()
    }
}
fun main() {
  //Adaptor
    val thirdPartyService = ThirdPartyService()
    val adapter = ServiceAdapter(thirdPartyService)
    val client = Client(adapter)
    val result = client.useService()
    println("Client received: $result")
}
