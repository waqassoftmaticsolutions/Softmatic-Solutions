## Web Services
1) Web Services are a collection open-source protocols and standards (SOAP, UDDI, REST, XML-RPC) that are useful for the exchange of data between systems or  applications.
2) A  web service is basically required to provide interoperability, i.e. connecting various applications.
3) It allows different apps to communicate with each other and share the data and services among themselves.
4) Web services provide a standard for all the types of client applications to invoke functions on every type of app server.

## How actually web services works
It has two important things that are the Client and the Server. Here first the Client makes a request from the Server and then, the Server makes a response to the Client.

              client requests service
    Client                                Server --> Server hosting web service
              Server response to client 

## Android Web Services Components
The web server architecture generally contains the following three roles-

1) Publisher
  The publisher can be understood as a Service provider. The publisher is responsible for creating the web service and making it available for the Clients.
2) Subscriber
  The Subscriber is nothing but, the service requester. The Service requester is the one that needs to contact the web service. The client application will contact through a client application. This Client application can be based on .Net or any language based language.
3) Broker
   The broker here is the application that provides access to the UDDI. The UDDI stands for User descriptive, discovery and integration. It enables the client application to locate the web service exactly.
   
      1) Publish --> Publishers Publishing the web services means informing the broker about its existence. It is done using the Broker’s interface to make is easily accessible to the subscribers
      2) Subscribe --> The Subscriber will consult the broker to locate the published web service easily .
      3) Bind --> Once the information regarding the web services is gained from the broker, the subscriber can bind the web service.

## Characteristics of Web Services in Android
1) Web services are loosely coupled
2) Web services are XML – based.
3) Web services have the ability to be either Synchronous or Asynchronous. In synchronous web services, the client is bound to the execution of the service. This means that when a client makes a request to a web service, it waits (or blocks) until it receives a response from the server before continuing with other tasks. In asynchronous web services, the client is not bound to the execution of the service. The client can make a request to the web service and then continue executing other tasks without waiting for the response.
4) Web Services supports Remote Procedure Calls. Remote Procedure calls can often be referred to as RPCs. These RPCs let the clients invoke various functions, methods, and services on remote objects using XML.

## Types of Web Services in Android
1) SOAP
   1) Simple Object Access Protocol
   2) It is an XML based protocol for accessing web services over HTTP or SMTP
   3) SOAP is W3C (World Wide Web Consortium) recomendation for communication between applications
   4) It is plateform independent and language independent
   5) You will be able to interact with other programming language
2) UDDI
    1) Universal Description Discovery and Integration
    2) It is an XML- based standard used for detailing, publishing and discovering new web services.
    3) Capabilities for sharing informations about web services within an organization's intranet between business partners on an extranet or on the internet
3) REST
    1) RESTFUL API
    2) REST is Representational State Transfer.
    3) It is an architrectural style for an Application Program Interface (API) HTTP request to access and user data
    4) REST technology is preffered over other similar technologies because REST use less bandwidth, making it more suitable for efficient internet usage.
    5) RESTFUL APIs can be built using any technology
4) XML-RPC
    1) In XML-RPC, RPC stands for remote procedure calls.
    2) It is among simplest and most foolproof web service approach that make it easy for computers to call procedures on other computers remotely.
    3) Remote procedure calls are one of the finest ways for the exchange of documents and information between computers.
    4) It uses XML messages to perform Remote Calls.
    5) XML- RPC is platform-independent as well as Language independent.
    6) It also allows diverse applications to communicate with each other.

## Advantages of using Web Services
1) Web services enable interoperability among different Applications.
2) Web services offer faster communications within and across applications and organizations.
3) They allow us to expose the functions of the existing code over the internet.
4) One of the very important advantages of using web services is Reusability.

## Limitations of Web Services over Android
1) The HTTP protocol used by web services is not reliable and is insecure.
2) Web services do not access from the browser.