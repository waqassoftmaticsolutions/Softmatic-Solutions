# ListView in Compose

We wil use LazyColumn scope in this to show our data in scrollable form.

```kotlin
@Composable
fun MyApp3(modifier: Modifier = Modifier, names: List<String> = List(1000){"$it"}){
    Surface(modifier = Modifier.background(white)){
        Column(modifier = Modifier.padding(8.dp)) {
            LazyColumn {
                item{Text("Header")}//this is used to giving header in the recyclerview or listview
                items(names) { name ->
                    ListView(name)
                }
            }
        }
    }
}

@Composable
fun ListView(name:String){
    //val expended = mutableStateOf(false)
    val expended = remember {
        mutableStateOf(false)
    }
    val extraPadding = if(expended.value) 50.dp else 0.dp
    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp) ){
        Row(modifier = Modifier.padding(24.dp)){
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            OutlinedButton(onClick = { expended.value = !expended.value }) {
                Text( if(expended.value) "Show less" else "Show More" , color = white)
            }
        }
    }
}
```
