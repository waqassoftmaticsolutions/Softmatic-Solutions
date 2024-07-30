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


## Difference b/w RESTful and SOAP
The architecture of SOAP (Simple Object Access Protocol) involves several components and standards that work together to enable communication between distributed systems. Here’s a detailed overview of SOAP architecture:

### **1. SOAP Envelope**

- **Purpose**: The SOAP envelope is the root element of a SOAP message. It defines the start and end of the message and contains two main parts: the header and the body.
- **Structure**:
  - **Header**: Optional. Contains metadata or control information like authentication, message routing, and transaction management.
  - **Body**: Required. Contains the actual message or request/response data. It holds the payload of the SOAP message.

  ```xml
  <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope">
      <soap:Header>
          <!-- Optional header information -->
      </soap:Header>
      <soap:Body>
          <!-- The actual message content -->
      </soap:Body>
  </soap:Envelope>
  ```

### **2. SOAP Header**

- **Purpose**: The SOAP header is an optional part of the envelope that contains metadata for processing the message. It can include information such as security credentials, transaction identifiers, and routing information.
- **Characteristics**:
  - **Extensible**: Can be used to add various types of metadata.
  - **Optional**: Not all SOAP messages include a header.

  ```xml
  <soap:Header>
      <m:Authentication xmlns:m="http://www.example.com/auth">
          <m:Username>user</m:Username>
          <m:Password>pass</m:Password>
      </m:Authentication>
  </soap:Header>
  ```

### **3. SOAP Body**

- **Purpose**: The SOAP body is the required part of the envelope that contains the main message content. It carries the request or response payload.
- **Characteristics**:
  - **Contains the Main Data**: The actual data or instructions being transmitted.
  - **Error Handling**: If there are faults or errors, they are included in the body.

  ```xml
  <soap:Body>
      <m:GetUserResponse xmlns:m="http://www.example.com/user">
          <m:User>
              <m:UserId>123</m:UserId>
              <m:UserName>John Doe</m:UserName>
          </m:User>
      </m:GetUserResponse>
  </soap:Body>
  ```

### **4. SOAP Fault**

- **Purpose**: The SOAP fault is an optional element within the body used to indicate errors or problems that occurred during message processing.
- **Structure**:
  - **faultcode**: Indicates the type of error.
  - **faultstring**: Provides a human-readable description of the error.
  - **faultactor**: Identifies the actor that caused the fault.
  - **detail**: Contains additional information about the fault.

  ```xml
  <soap:Fault>
      <faultcode>soap:Server</faultcode>
      <faultstring>Server encountered an error</faultstring>
      <detail>
          <m:ErrorDetail xmlns:m="http://www.example.com/error">
              <m:Message>Invalid input data</m:Message>
          </m:ErrorDetail>
      </detail>
  </soap:Fault>
  ```

### **5. WSDL (Web Services Description Language)**

- **Purpose**: WSDL is an XML-based language used to describe the functionality offered by a SOAP web service. It defines the operations available, the input and output messages, and the protocols used.
- **Components**:
  - **Types**: Data types used by the web service.
  - **Messages**: Definitions of the data being exchanged.
  - **Port Types**: Operations supported by the service.
  - **Bindings**: Details on how the service is accessed (e.g., SOAP over HTTP).
  - **Services**: Addresses where the service can be accessed.

  ```xml
  <wsdl:definitions xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                    xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                    xmlns:xsd="http://www.w3.org/2001/XMLSchema">
      <wsdl:message name="GetUserRequest">
          <wsdl:part name="parameters" element="xsd:GetUser"/>
      </wsdl:message>
      <wsdl:message name="GetUserResponse">
          <wsdl:part name="parameters" element="xsd:GetUserResponse"/>
      </wsdl:message>
      <wsdl:portType name="UserServicePortType">
          <wsdl:operation name="GetUser">
              <wsdl:input message="tns:GetUserRequest"/>
              <wsdl:output message="tns:GetUserResponse"/>
          </wsdl:operation>
      </wsdl:portType>
      <wsdl:binding name="UserServiceBinding" type="tns:UserServicePortType">
          <soap:binding transport="http://schemas.xmlsoap.org/soap/http"/>
          <wsdl:operation name="GetUser">
              <soap:operation soapAction="http://www.example.com/GetUser"/>
              <wsdl:input>
                  <soap:body use="literal"/>
              </wsdl:input>
              <wsdl:output>
                  <soap:body use="literal"/>
              </wsdl:output>
          </wsdl:operation>
      </wsdl:binding>
      <wsdl:service name="UserService">
          <wsdl:port name="UserServicePort" binding="tns:UserServiceBinding">
              <soap:address location="http://www.example.com/UserService"/>
          </wsdl:port>
      </wsdl:service>
  </wsdl:definitions>
  ```

### **6. SOAP Protocols and Standards**

- **Transport Protocol**: SOAP messages are typically transported over HTTP or HTTPS, but they can also be sent over other protocols like SMTP or JMS.
- **Encoding**: SOAP uses XML encoding for data representation.
- **Security**: SOAP can use WS-Security to provide various security features like encryption, digital signatures, and token-based authentication.

### **Summary**

The SOAP architecture consists of the following key components:
- **SOAP Envelope**: Contains the header and body of the message.
- **SOAP Header**: Optional metadata and control information.
- **SOAP Body**: Contains the actual message content and faults.
- **SOAP Fault**: Indicates errors and provides details about them.
- **WSDL**: Describes the web service’s functionality and how to interact with it.
- **Protocols and Standards**: Defines how SOAP messages are transported and secured.

These components work together to ensure that SOAP provides a robust and formal mechanism for communication between distributed systems.
