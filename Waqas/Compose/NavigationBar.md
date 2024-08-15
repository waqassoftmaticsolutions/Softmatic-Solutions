# Navigation Bar and Floating action button in Compose
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ComposeApplicatiomTheme {
                Scaffold(topBar = {
                    TopAppBar (
                        title = {
                            Text(text = "My App")
                        },
                        navigationIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Filled.Menu,contentDescription = "Menu")
                            }
                        },
                        actions = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Filled.Notifications, contentDescription = "notification")
                            }
                            IconButton(onClick = { /*TODO*/})  {
                                Icon(Icons.Filled.Search, contentDescription = "search")
                            }
                        },
                    )

                },
                floatingActionButton = {
                        FloatingActionButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.Add, contentDescription = "add")
                        }
                    }
                )
                {
                    Greeting(name="Waqas",Modifier.background(color = Purple40, shape= RectangleShape))
                }
        }
    }
}


@Composable
fun MyApp(){
    Surface(color = MaterialTheme.colorScheme.primary){
        Greeting(name = "Muhammad Waqas")
    }
}
```
