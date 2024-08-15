# States in Compose

```kotlin
@Composable
fun MyApp2(modifier: Modifier = Modifier, names: List<String> = listOf("World", "Compose")){
    Surface(modifier = Modifier.background(white)){
        Column(modifier = Modifier.padding(8.dp)) {
            for(name in names){
                Greeting2(name = name)
            }
        }
    }
}
@SuppressLint("UnrememberedMutableState")
@Composable
fun Greeting2(name:String){
    //val expended = mutableStateOf(false)
    var expended by remember {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState(
        targetValue = if(expended) 50.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 2000
        )
    )
    // Row is used to make a single row and column in used to make a single column
    // Here in this there will be a single row and in this row there will be 1  single column on left 2 texts and 1 button on right.
    // .weight(1f) means they will take the space as they need. 
    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp) ){
        Row(modifier = Modifier.padding(24.dp)){
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello, ")
                Text(text = name)
//                if(expended.value){
//                    Text(text = "$name Extra Padding")
//                }else{
//                    Text(text = name)
//                }
            }
            OutlinedButton(onClick = { expended = !expended}) {
                Text( if(expended) "Show less" else "Show More" , color = white)
            }
        }
    }
}
```
