# Web Services
#### A web service is basically required to provide interoperability, i.e. connecting various applications. It allows different apps to communicate with each other and share the data and services among themselves. Web services provide a standard for all the types of client applications to invoke functions on every type of app server.

For example, you can consider an android application interacting with a .NET app using a web service.

Android Web Services Components

## The web server architecture generally contains the following three roles-

### a. Publisher
The publisher can be understood as a Service provider. The publisher is responsible for creating the web service and making it available for the Clients.

### b. Subscriber
The Subscriber is nothing but, the service requester. The Service requester is the one that needs to contact the web service. The client application will contact through a client application. This Client application can be based on .Net or any language based language.

### c. Broker
The broker here is the application that provides access to the UDDI. The UDDI stands for User descriptive, discovery and integration. It enables the client application to locate the web service exactly.


## The services that do are as follows:

### a. Publish

Publishers Publishing the web services means informing the broker about its existence. It is done using the Broker’s interface to make is easily accessible to the subscribers

### b. Subscribe

The Subscriber will consult the broker to locate the published web service easily .

### c. Bind

Once the information regarding the web services is gained from the broker, the subscriber can bind the web service.

## Characteristics of web services:

1. **XML-Based**:
   - Web services use XML (Extensible Markup Language) to represent and transfer data. 
   - XML helps these services work across different networks, operating systems, and platforms, making them highly compatible and able to work together easily.

2. **Loosely Coupled**:
   - The client (consumer) and the service provider are not directly linked.
   - This means changes in one part do not necessarily affect the other, allowing each to be updated or modified independently.

3. **Synchronous and Asynchronous**:
   Web services have the ability to be either Synchronous or Asynchronous. 
   - **Synchronous**: The client waits for the service to complete its task before continuing. It’s like making a phone call and waiting on the line until you get the response.
   - **Asynchronous**: The client can send a request and continue doing other tasks while waiting for the response. It’s like sending a text message and continuing your activities without waiting for a reply.

5. **Remote Procedure Calls (RPCs)**:
   - Web services can perform operations on remote servers as if they were local, using something called Remote Procedure Calls.
   - RPCs allow clients to call functions or methods that are hosted on a remote machine, using XML to communicate.

6. **Document Exchange Support**:
   - Web services can handle complex data structures and documents.
   - XML provides a flexible way to represent data, making it suitable for exchanging various types of documents and data structures.
  
## XML Remote Procedure Calls
 Remote procedure calls are one of the finest ways for the exchange of documents and information between computers. Let us see some things about XML-RPC:

1. It uses XML messages to perform Remote Calls.
2. These requests are encoded in XML and are transferred via http POST.
3. The XML responses are embedded in the same way as http responses.
4. XML-  RPC is platform-independent as well as Language independent.
5. It also allows diverse applications to communicate with each other.
6. It is developed using W3C(World Wide Web Consortium) standards.

### In Summary

Web services are designed to be highly interoperable and flexible. They can communicate across different platforms, handle complex data, and allow clients to either wait for a response or continue with other tasks while waiting. This makes them versatile and powerful tools for connecting different parts of an application or different applications altogether.

## Types of Web Services in Android

### 1. XML-RPC

 - In XML-RPC, RPC stands for remote procedure calls. It is an XML based protocol for the exchange of data between a huge range of devices over the internet.
 - XML-RPC permits programs to make functions or procedure calls across a network
 - Use HTTP Protocol to pass information from a client computer to server computer

### 2. UDDI

- UDDI stands for Universal Descriptive, discovery, and integration. It is an XML- based standard used for detailing, publishing and discovering new web services.
- Capabilities for sharing information about web services within an organization intranet,between business partners on an extranet, or on the internet
- Facilitates the integration of different web services by providing a common directory.

### 3. SOAP

 - SOAP here stands for Simple object access protocol. It is an XML based web service protocol used for the exchange of data or documents over HTTP(Hypertext transfer protocol) or SMTP(Simple Message Transfer Protocol). 
- It allows the communication of independent processes that operate on disparate systems.
- Has some specifications used across all applications
- It is W3C recommended for communication b/w two applications
- it is platfrom independent and language idependent
- you will we able to interact other programming languages

### 4. REST


- REST is Representational State Transfer. It provides communication and connectivity between devices and the internet.
- REST is an architectural style for designing networked applications. It uses standard HTTP methods and principles to interact with resources and is often used for web APIs.

### Key Features:

#### Stateless: 
Each request from the client to the server must contain all the information needed to understand and process the request. The server does not store client context between requests.
#### Resource-Based: 
Resources (such as users, orders) are identified by URLs. RESTful services expose resources using HTTP methods:
GET: Retrieve a resource.
POST: Create a new resource.
PUT: Update an existing resource.
DELETE: Remove a resource.
#### Flexible Data Formats: 
While REST commonly uses JSON, it can support other formats like XML, HTML, or plain text.
#### Simplicity and Scalability: 
REST is designed to be simple and scalable, making it suitable for web services that need to handle large amounts of traffic.

### Advantages of Web Services

1. Web services enable **interoperability** among different Applications.
2. One of the very important advantages of using web services is **Reusability**.
3. Web services offer **faster communications** within and across applications and organizations.
4. They **use a quality industry-standard protocol** to enable communication between different applications.
5. They use SOAP over HTTP to enable the use of **low-cost internet** for implementing web services.
6. Web Services are deployed over the standard internet technologies.
7. They allow us to **expose the functions of the existing code** over the internet.

### Android Web Services Limitations

1. Web services do not access from the browser.
2. They don’t leverage emerging Web developments(Web services might not take full advantage of the latest technologies and advancements on the web.)
3. The HTTP protocol used by web services is not reliable and is insecure.

