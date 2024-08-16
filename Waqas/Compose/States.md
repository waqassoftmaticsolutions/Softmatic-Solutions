```kotlin
//Statefull
@Composable
fun NotificationScreen(){
    var count = rememberSaveable {
        mutableStateOf(0)
    }
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(1f)){
        NotificationCounter(count.value) { count.value++ }
        MessageBar(count.value)
    }
}

//Stateless
@Composable
fun MessageBar(count: Int){
    Column(verticalArrangement = Arrangement.Center){
        Text(text = "Message sent so far $count")
    }
}

//Stateless
@Composable
fun NotificationCounter(value: Int, increment: () -> Unit) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "You have sent $value notifications")
        Button(onClick = { increment() }, colors = ButtonDefaults.buttonColors(
            contentColor = Color.LightGray
        ) ) {
            Text(text = "Send Notification")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationCounterPreview() {
    CodeLab4Theme {
        NotificationScreen()
    }
}
@Preview(showBackground = true)
@Composable
private fun MessageBarPreview() {
    CodeLab4Theme {
        MessageBar(0)
    }
}
```
