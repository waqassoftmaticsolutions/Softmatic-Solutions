# Data Binding
Data Binding is basically one of the components of Jetpack architecture that we use to eliminate the standard view, bind data to views and enable us to write faster and more organized code for data-driven user interfaces.
## Why we use it?
1) Provides a way to connect data with UI without manual intervention
2) As the number of data increases, it makes it easier to synchronize the user interface compared to other methods
3) The entire interface is connected at the first start of the application so that the view hierarchy is not scanned repeatedly
4) Easier to maintain with reduced number of codes and increased readability

## Code Example
First we have to add dependency in build.gradle
```text
buildFeatures {
            dataBinding true
}
```
### activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable
            name="viewModel"
            type="com.example.myapplication.DataBindViewModel" />
    </data>
    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".DataBindActivity">

        <TextView
            android:id="@+id/hello_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewModel.helloText}"
            android:textSize="30sp"
            android:textStyle="bold"
            android:typeface="sans"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintHorizontal_bias="0.498"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.147" />

        <TextView
            android:id="@+id/name_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="30sp"
            android:textStyle="bold"
            android:typeface="sans"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintHorizontal_bias="0.498"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.235" />

        <EditText
            android:id="@+id/name_edit_text"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_margin="20dp"
            android:ems="10"
            android:hint="Write your name"
            android:inputType="textPersonName"
            android:text="@={viewModel.userName}"
            android:textSize="30sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.6"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/name_text_view"
            app:layout_constraintVertical_bias="0.068" />

        <Button
            android:id="@+id/submit_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Submit"
            android:textSize="30sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.541"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.499" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

### DataBindViewModel
```kotlin
//package com.example.myapplication
//import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class DataBindViewModel : ViewModel() {
//    val userName = MutableLiveData<String>()
//    val helloText = MutableLiveData<String>()
//    init {
//        userName.value = ""
//        helloText.value = "Hello, Data Binding!"
//    }
      var userName = ""
      var helloText = "Hello, Data Binding!"
}

```
### MainActivity.kt
```kotlin
package com.example.myapplication
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DataBindViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.submitButton.setOnClickListener {
            displayHello()
        }
    }
    private fun displayHello() {
        //viewModel.helloText.value = "Hello"
        binding.helloTextView.text = "Hello"
        binding.nameTextView.text = binding.nameEditText.text.toString()
    }
}
```

# Two Way Data Binding
Two-Way data binding allows you to send objects to the binding object of your layout. 
Thus, you can provide an instant view. If you ask what is the difference from the previous Data Binding, 
all the difference is formed by “ = “. So here is the **syntax (@ = {variable} )**
## Code Example
First we have to add dependency in build.gradle
```text
buildFeatures {
            dataBinding true
}
```
### activity_two_way_binding.xml
```xml
<!-- res/layout/activity_main.xml -->
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>
        <!-- Declare a variable to bind data -->
        <variable
            name="viewModel"
            type="com.example.myapplication.TwoWayBindingModel" />
    </data>
    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@{viewModel.text}"
        android:textSize="30sp"
        android:textStyle="bold"
        android:typeface="sans"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.147"/>

        <EditText
            android:id="@+id/editText"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintHorizontal_bias="0.498"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_below="@id/textView"
            android:text="@={viewModel.text}" />
    </RelativeLayout>
</layout>
```
### TwoWayBindingModel.kt
```kotlin
package com.example.myapplication
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
class TwoWayBindingModel : ViewModel(){
    val text = ObservableField<String>("Hello, Two-Way Data Binding!")
}
```
### TwoWayBinding.kt
```kotlin
// src/main/java/com/example/app/MainActivity.kt
package com.example.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
//import com.example.app.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityTwoWayBindingBinding
class TwoWayBinding : AppCompatActivity() {
    private lateinit var binding: ActivityTwoWayBindingBinding
    private lateinit var viewModel: TwoWayBindingModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TwoWayBindingModel::class.java)
        binding = ActivityTwoWayBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
```
