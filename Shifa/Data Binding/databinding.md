# Introduction
The most basic step in developing an application is to take the data and set it up to display in the user interface. The most frequently used method for Android applications was to find the element in the layout file with findViewbyID, define it to a local variable, and assign a value to an element by taking the value from the data. Moreover, you had to repeat this over and over for every element containing data in the layout.
# What is Data Binding?
Data Binding entered our lives when it was first introduced as Support Library at Google I/O 2015. Data Binding is basically one of the components of Jetpack architecture that we use to eliminate the standard view, bind data to views and enable us to write faster and more organized code for data-driven user interfaces.
# Why we need it?
1. Provides a way to connect data with UI without manual intervention
2. As the number of data increases, it makes it easier to synchronize the user interface compared to other methods
3. Model and interface are completely separated by getting rid of UI common codes in Activity or Fragments
4. The entire interface is connected at the first start of the application so that the view hierarchy is not scanned repeatedly
5. Faster as binding is not done in onCreate
6. Safer to import UI components as binding is not done at runtime
7. Easier to maintain with reduced number of codes and increased readability
So,
### Let`s say good bye to findViewById and say hello to Data Bindig!

```kotlin
android {
    ....
   buildFeatures {
            dataBinding true
}
```

### activity_main.xml
```kotlin
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="user"
            type="com.example.myapplication.User" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="16dp"
        android:gravity="center"
        android:background="#FFC0CB"
        tools:ignore="ExtraText">

        <!-- TextView for displaying the user's name -->
        <TextView
            android:id="@+id/userNameTextView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="18sp"
            android:hint="Enter your name"
            android:text="@{user.name}"
            android:layout_marginBottom="8dp"
            android:background="#FFC0CB" />

        <!-- TextView for displaying the user's age -->
        <TextView
            android:id="@+id/userAgeTextView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="16sp"
            android:hint="Enter your age"
            android:text="@{String.valueOf(user.age)}"
            android:layout_marginBottom="16dp"
            android:background="#FFC0CB" />

        <!-- Submit button -->
        <Button
            android:id="@+id/submitButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Submit"
            android:background="#FF4081"
            android:textColor="#000000"
            android:layout_gravity="center" />

    </LinearLayout>
</layout>
```
### User.kt
```kotlin
package com.example.myapplication

data class User(var name:String, var age:Int)
```

### MainActivity.kt
```kotlin
package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val user = User("Moiz Khan", 20) //ye data UI k textview ma show kray ga
        binding.user = user
    }
}
```
