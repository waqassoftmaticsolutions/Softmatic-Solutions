## Android Architecture
Android architecture contains a different number of components to support any Android device’s needs. 
Components of Android Architecture
The main components of Android architecture are the following:-
1) Applications
2) Application Framework
3) Android Runtime
4) Platform Libraries
5) Linux Kernel

## Applications
1) These are the end-user applications installed on the device, such as email, SMS, calendar, maps, and more.
2) It runs within android runtime with the help of classes and services provided by application framework.
    1) Home
    2) Contacts
    3) Camera
    4) Clock
    5) Calendar
    6) Gallery
    ### Advantages
     1) **User-Focused** --> Directly serve the needs of the user
     2) **Wide Variety** --> Thousands of applications are available, catering to vast range of functiopns
    ### Disadvantages
     1) **Security Risks** --> Malicious applications can pose security threats.
     2) **Resource Intensive** --> Some applications can consume a lot of resources, affecting device performance.

## Application Framework
1) Application Framework provides several important classes which are used to create an Android application.
2) The application framework provides a set of APIs for developers to build applications.
   It includes
   1) Activity Manager --> Manages the lifecycle and navigation stack of activities in an Android application, ensuring that activities are properly started, stopped, and resumed as the user navigates through the app.
   2) Window Manager --> Manages the windows and their layout on the screen, handling the positioning, size, and appearance of windows for both applications and the system UI.
   3) Content Providers --> Manages access to a structured set of data, enabling applications to share data with each other in a secure and controlled manner through a standardized interface.
   4) View System --> Provides the building blocks for the user interface components, allowing developers to create and manipulate UI elements such as buttons, text fields, and other interactive widgets.
   5) Package Manager --> Handles the installation, updating, and removal of applications, as well as querying and managing information about installed packages and their components.
   6) Telephony Manager --> Provides access to information about the telephony services on the device, such as network details, SIM card status, and call-related functionalities.
   7) Resource Manager --> Manages access to application resources like strings, drawable images, and layout files, facilitating the loading and manipulation of these resources at runtime.
   8) Location Manager --> Provides access to the device's location services, enabling applications to obtain periodic updates of the device's geographical position.
    ### Advantages
     1) **Simplifies Development:** Provides a consistent framework to build applications.
     2) **Reusability:** Allows developers to use system services and components easily.
    ### Disadvantages
     1) **Learning Curve:** Requires developers to understand the framework's components and lifecycle.
     2) **Performance Overhead:** Abstraction can sometimes lead to performance overhead.

## Android Runtime
1) Android Runtime environment is one of the most important part of Android. 
2) It contains components like core libraries and the Dalvik virtual machine(DVM).
3) Mainly, it provides the base for the application framework and powers our application with the help of the core libraries.
4) Like Java Virtual Machine (JVM), Dalvik Virtual Machine (DVM) is a register-based virtual machine and specially designed and optimized for android to ensure that a device can run multiple instances efficiently.
5) The core libraries enable us to implement android applications using the standard JAVA or Kotlin programming languages.
    1) DVM
    2) Corelibraries
    ### Advantages
     1) **Performance:** Ahead-of-Time (AOT) compilation improves application performance and reduces start-up time.
     2) **Memory Management:** More efficient garbage collection compared to Dalvik.
    ### Disadvantages
     1) **Storage:** AOT compilation increases the storage space required for applications.
     2) **Complexity:** Introduces additional complexity in debugging and profiling applications.
  
## Platform Libraries
1) The Platform Libraries includes various C/C++ core libraries and Java based libraries such as Media, Graphics, Surface Manager, OpenGL etc. to provide a support for android development.
2) They provide functionalities like media playback, graphics rendering, databases, and web browsing.
     1) **Media** library provides support to play and record an audio and video formats.
     2) **Surface manager** responsible for managing access to the display subsystem.
     3) **SGL and OpenGL** both cross-language, cross-platform application program interface (API) are used for 2D and 3D computer graphics.
     4) **SQLite** provides database support and FreeType provides font support.
     5) **Web-Kit** This open source web browser engine provides all the functionality to display web content and to simplify page loading.
     6) **SSL (Secure Sockets Layer)** is security technology to establish an encrypted link between a web server and a web browser.
    ### Advantages
     1) **Performance:** Native libraries offer better performance for critical operations.
     2) **Rich Features:** Provide a wide range of functionalities out of the box.
    ### Disadvantages
     1) **Security:** Native code is more prone to security vulnerabilities.
     2) **Complexity:** Debugging and maintaining native code can be more challenging.

## Linux Kernel
1) Linux Kernel is heart of the android architecture. 
2) It manages all the available drivers such as display drivers, camera drivers, Bluetooth drivers, audio drivers, memory drivers, etc. which are required during the runtime. 
     1) **Security:** The Linux kernel handles the security between the application and the system.
     2) **Memory Management:** It efficiently handles the memory management thereby providing the freedom to develop our apps.
     3) **Process Management:** It manages the process well, allocates resources to processes whenever they need them.
     4) **Network Stack:** It effectively handles the network communication.
     5) **Driver Model:** It ensures that the application works properly on the device and hardware manufacturers responsible for building their drivers into the Linux build.
    ### Advantages
     1) **Security:** Provides a robust security model with user permissions and process isolation.
     2) **Hardware Abstraction:** Handles interactions with hardware, providing a consistent interface for the higher layers.
    ### Disadvantages
     1) **Complexity:** The kernel is complex and requires specialized knowledge to modify or debug.
     2) **Overhead:** The use of Linux Kernel adds some overhead compared to a more lightweight, custom kernel.
