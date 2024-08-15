# Make switch in Compose

```kotlin
@SuppressLint("RememberReturnType")
@Composable
fun ShowSwitch(){
    // This is to set the state of the switch
    val isChecked = remember{
        mutableStateOf(true)
    }
    // switch tag to make a switch in compose
    // checked is used to set the current state
    // onCheckedChange used to set the state when change occure in it
    Switch(
        checked = isChecked.value,
        onCheckedChange = {
        isChecked.value = it
    },
    modifier = Modifier.run {
        size(20.dp)
        padding(100.dp)
    })
}
```
