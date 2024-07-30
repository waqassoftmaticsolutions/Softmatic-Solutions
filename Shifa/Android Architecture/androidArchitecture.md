# Android architecture
Android architecture contains a different number of components to support any Android device’s needs.It provides a way to understand how Android applications interact with the operating system and how they are organized. 
Components of Android Architecture
### The main components of Android architecture are the following:-

1) Applications
2) Application Framework
3) Android Runtime
4) Platform Libraries
5) Linux Kernel
### Applications
Applications is the top layer of android architecture. 
#### Role: 
The end-user applications that run on the Android system. These can be both pre-installed system apps and user-installed apps.
#### Components:
System Apps: Essential apps like Phone, Contacts, and Settings.
User Apps: Apps downloaded from the Google Play Store or other sources.
### Application Framework
#### Role: 
Provides high-level APIs and services that Android applications use.This layer provides the building blocks to create Android apps. It includes a variety of services and tools that help developers build apps.
#### Components:
**Activity Manager:** 
Manages the lifecycle of applications and activities.
#### **Window Manager:** 
Manages the user interface and displays windows.
#### **Content Providers:** 
Manage data access and sharing between applications.
#### **Notification Manager:** 
Handles notifications and alerts.
#### **Package Manager:** 
Manages application installation and updates.
#### Andriod Runtime
Android Runtime environment is one of the most important part of Android. 
**It contains components like:** 
1) core libraries
2) the Dalvik virtual machine(DVM).
    ### 
Mainly, it provides the base for the application framework and powers our application with the help of the core libraries. Like Java Virtual Machine (JVM), Dalvik Virtual Machine (DVM) is a register-based virtual machine and specially designed and optimized for android to ensure that a device can run multiple instances efficiently.
It depends on the layer Linux kernel for threading and low-level memory management. The core libraries enable us to implement android applications using the standard JAVA or Kotlin programming languages.
### Platform Libraries
#### What It Means: 
This layer includes various libraries that support different functions in Android apps.
#### Key Libraries:
##### Media Library: 
Helps in playing and recording audio and video.
##### Surface Manager: 
Manages how things are displayed on the screen.
##### OpenGL: 
Used for creating graphics and games.
##### SQLite: 
Provides database support for storing data.
##### WebKit: 
Used for displaying web content.
##### SSL: 
Ensures secure communication over the internet.
### Linux Kernel
Linux Kernel is heart of the android architecture. It manages all the available drivers such as display drivers, camera drivers, Bluetooth drivers, audio drivers, memory drivers, etc. which are required during the runtime. 
The Linux Kernel will provide an abstraction layer between the device hardware and the other components of android architecture. It is responsible for management of memory, power, devices etc. 
#### The features of Linux kernel are:

- Security: The Linux kernel handles the security between the application and the system.
- Memory Management: It efficiently handles the memory management thereby providing the freedom to develop our apps.
- Process Management: It manages the process well, allocates resources to processes whenever they need them.
- Network Stack: It effectively handles the network communication.
- Driver Model: It ensures that the application works properly on the device and hardware manufacturers responsible for building their drivers into the Linux build.


### **Commonly Used Architectures in Android**

1. **MVC (Model-View-Controller)**
   - **What It Means**: This design pattern divides the app into three parts:
     - **Model**: Manages the data /  Store the data(e.g., a list of contacts).
     - **View**: UI layer that holds the component visible on the screen (e.g., the screen where contacts are displayed).
     - **Controller**: Connects the Model and the View (e.g., the code that updates the UI when data changes).

2. **MVP (Model-View-Presenter)**
   - **What It Means**: A variation of MVC that makes it easier to manage and test:
     - **Model**: Manages the data.
     - **View**: The user interface.
     - **Presenter**: Acts as the middleman, handling the logic and updating the View based on the Model. The Presenter and View communicate through an interface.

3. **MVVM (Model-View-ViewModel)**
   - **What It Means**: Another variation that separates the UI from the data:
     - **Model**: Manages the data.
     - **View**: The user interface.
     - **ViewModel**: Handles the logic and prepares data for the View. It does not directly reference the View but uses data binding techniques to update the UI.

### Summary

- **Android Architecture**: Includes layers like Applications (apps), Application Framework (tools for building apps), Android Runtime (where code runs), Platform Libraries (support libraries), and Linux Kernel (core system functions).
- **MVC**: Divides an app into Model, View, and Controller.
- **MVP**: Uses Model, View, and Presenter to manage app logic and UI.
- **MVVM**: Separates Model, View, and ViewModel for better management and data binding.

These architectures help structure Android apps in a way that makes them easier to develop, maintain, and test.
