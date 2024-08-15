@SuppressLint("UnrememberedMutableState")
@Composable
fun WaterCounter(modifier:Modifier = Modifier){
   Column(modifier = modifier.padding(16.dp)) {
       var count by remember {mutableStateOf(0)}
      Text("You've has $count glasses. ")
      Button(onClick ={count ++},Modifier.padding(top = 8.dp)){
         Text("Add one")
      }
   }
}
@Preview(showBackground = true)
@Composable
fun WaterCounterPreview(){
   ComposeStateTheme {
      WaterCounter()
   }
}
