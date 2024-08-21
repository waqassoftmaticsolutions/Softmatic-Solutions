# Context
The Circumstances that form the setting for an Event, Statement, or Idea, and in terms of which it can be fully understood.
1) The Context tells us about the surrounding information.
2) It is very important to understand the environment which we want to understand.

We can break the Context and its use into three major parts:
1) It allows us to access resources.
2) It allows us to interact with other Android components by sending messages.
3) It gives you information about your app environment.

In android, Context is the main important concept and the wrong usage of it leads to memory leakage. Activity refers to an individual screen and Application refers to the whole app and both extend the Context class. 
## Types of Context in Android
1) Application
2) Activity
The Overall view of the App hierarchy looks like the following:
![MockupScreenOfApplicationAndActivity](https://github.com/user-attachments/assets/20ff607b-5c45-4099-b135-eb5c5abdca49)

### Appliation Context
This Context is tied to the Lifecycle of an Application. Mainly it is an instance that is a singleton and can be accessed via getApplicationContext(). Some use cases of Application Context are:

1) If it is necessary to create a singleton object
2) During the necessity of a library in an activity
#### getApplicationContext()
It is used to return the Context which is linked to the Application which holds all activities running inside it. When we call a method or a constructor, we often have to pass a Context and often we use “this” to pass the activity Context or “getApplicationContext” to pass the application Context. This method is generally used for the application level and can be used to refer to all the activities. For example, if we want to access a variable throughout the android app, one has to use it via getApplicationContext().
