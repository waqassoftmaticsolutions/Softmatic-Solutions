package com.example.composeapplicatiom

import androidx.activity.compose.setContent

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.R

import com.example.composeapplicatiom.ui.theme.ComposeApplicatiomTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicatiomTheme {
                //MyApp()
                //MyAppPotrait()
                //MyAppLandscape()
                val windowSizeClass = calculateWindowSizeClass(this)
                MySootheApp(windowSizeClass)
            }
        }
    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search")
        },
        placeholder = {
            Text(text = "Enter something")
        }
    )
}

@Composable
fun AlignYouItemRow(
    modifier: Modifier = Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),//horizontal simple padding not using as this cut the last
        modifier = modifier
    ) {
        items(alignYouData){ item ->
            AlignYouBodyElement(
                drawable = item.drawable,
                text = item.text
            )
        }
    }
}

@Composable
fun AlignYouBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(88.dp)

        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun FavouriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier){
    Surface(
        shape = MaterialTheme.shapes.small
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier.width(192.dp)
        ){
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(56.dp)

            )
            Text(
                stringResource(id = text),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = 16.dp)

            )
        }
    }
}

@Composable
fun FavouriteCollectionGrid(
    modifier: Modifier = Modifier
){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.height(120.dp)

    ) {
        items(favouriteData){ item ->
            FavouriteCollectionCard(
                drawable = item.drawable,
                text = item.text
            )
        }
    }
}

//Slots API
@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
        Column(modifier) {
            Text(
                stringResource(id = title).uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                    .padding(horizontal = 16.dp)
            )
            content()
        }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    Column(
        modifier
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ){
        //Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.cars) {
            AlignYouItemRow()
        }
        HomeSection(title = R.string.favourite) {
            FavouriteCollectionGrid()
        }
    }
}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier){
    NavigationBar(modifier) {
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Default.Home, contentDescription = null)
            },
            label = {
                Text(stringResource(id = R.string.bottom_navigation_home))
            }
        )
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Default.AccountCircle, contentDescription = null)
            },
            label = {
                Text(stringResource(id = R.string.bottom_navigation_profile))
            }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyAppPotrait(){
    Scaffold(bottomBar = { BottomNavigation() }){
        ComposeApplicatiomTheme {
            HomeScreen()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyAppLandscape(){
//    Scaffold(bottomBar = {BottomNavigation()}){
//        ComposeApplicatiomTheme {
//            HomeScreen()
//        }
//    }
    ComposeApplicatiomTheme {
        Row {
                SootheNavigationRail()
                HomeScreen()
        }
    }
}
@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {}
            )

            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MyAppPotrait()
        }
        WindowWidthSizeClass.Expanded -> {
            MyAppLandscape()
        }
    }
}



private val alignYouData = listOf(
    R.drawable.img_1 to R.string.car1,
    R.drawable.img_2 to R.string.car2,
    R.drawable.img_3 to R.string.car3,
    R.drawable.img_4 to R.string.car4,
    R.drawable.img_5 to R.string.car5,
    R.drawable.img_6 to R.string.car6,
).map { DrawableStringPair(it.first, it.second) }

private val favouriteData = listOf(
    R.drawable.img_1 to R.string.car1,
    R.drawable.img_2 to R.string.car2,
    R.drawable.img_7 to R.string.car7,
    R.drawable.img_4 to R.string.car4,
    R.drawable.img_5 to R.string.car5,
    R.drawable.img_8 to R.string.car8,
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable:Int,
    @StringRes val text: Int
)

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SearchPreview() {
    ComposeApplicatiomTheme {
        SearchBar(Modifier.padding(8.dp))
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignmentPreview() {
    ComposeApplicatiomTheme {
        AlignYouBodyElement(
            drawable = R.drawable.img_1,
            text = R.string.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavouritePreview() {
    ComposeApplicatiomTheme {
        AlignYouItemRow()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignmentPreviewRow() {
    ComposeApplicatiomTheme {
        FavouriteCollectionCard(
            drawable = R.drawable.img_2,
            text = R.string.fc2_nature_meditations
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun GridPreviewRow() {
    ComposeApplicatiomTheme {
        FavouriteCollectionGrid()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    ComposeApplicatiomTheme {
        HomeSection(
            title = R.string.cars){
            AlignYouItemRow()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun HomeScreenPreview() {
    ComposeApplicatiomTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun BottomNavigationPreview() {
    ComposeApplicatiomTheme {
        BottomNavigation(Modifier.padding(top = 24.dp))
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SootheNavigationRailPreview() {
    ComposeApplicatiomTheme {
        SootheNavigationRail()
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MyAppPreview() {
    ComposeApplicatiomTheme {
        MyAppPotrait()
    }
}

@Preview(widthDp = 640, heightDp = 360, backgroundColor = 0xFFF5F0EE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(widthDp = 640, heightDp = 360, backgroundColor = 0xFFF5F0EE)
@Composable
fun MyAppLandscapePreview() {
    ComposeApplicatiomTheme {
        MyAppLandscape()
    }
}




//package com.example.composeapplicatiom
//
//import android.annotation.SuppressLint
//import android.content.res.Configuration
//import android.os.Bundle
//import android.view.Display.Mode
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.animation.core.Spring
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.animation.core.spring
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Switch
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.composeapplicatiom.ui.theme.ComposeApplicatiomTheme
//import com.example.composeapplicatiom.ui.theme.Purple40
//import com.example.composeapplicatiom.ui.theme.white
//
//class MainActivity : ComponentActivity() {
//    @OptIn(ExperimentalMaterial3Api::class)
//    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //enableEdgeToEdge()
//        setContent {
////            ComposeApplicatiomTheme {
////                Scaffold(topBar = {
////                    TopAppBar (
////                        title = {
////                            Text(text = "My App")
////                        },
////                        navigationIcon = {
////                            IconButton(onClick = { /*TODO*/ }) {
////                                Icon(Icons.Filled.Menu,contentDescription = "Menu")
////                            }
////                        },
////                        actions = {
////                            IconButton(onClick = { /*TODO*/ }) {
////                                Icon(Icons.Filled.Notifications, contentDescription = "notification")
////                            }
////                            IconButton(onClick = { /*TODO*/})  {
////                                Icon(Icons.Filled.Search, contentDescription = "search")
////                            }
////                        },
////                    )
////
////                },
////                    floatingActionButton = {
////                        FloatingActionButton(onClick = { /*TODO*/ }) {
////                            Icon(Icons.Filled.Add, contentDescription = "add")
////                        }
////                    }
////                )
////                {
////                    //Greeting(name="Waqas",Modifier.background(color = Purple40, shape= RectangleShape))
////
////                    Greeting(name="Waqas",Modifier.background(color = Purple40, shape= RectangleShape))
////                    ShowSwitch()
////                }
//            ComposeApplicatiomTheme {
//                //MyApp() // Simple
//                //MyApp2(modifier = Modifier.fillMaxSize()) //State
//                //CheckScreenActive() //OnBoarding Screen + Callback in it
//                //MyApp3()
//
//
//            }
//        }
//    }
//}

//
////My App
//@Composable
//fun MyApp(){
//    Surface(color = MaterialTheme.colorScheme.primary){
//        Greeting(name = "Muhammad Waqas")
//    }
//}
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun Greeting(name: String) {
//    Surface(color = MaterialTheme.colorScheme.primary){
//        Text(
//            text = "Hello $name!",
//            modifier = Modifier.padding(10.dp),
//            color = white
//        )
//    }
//}
//
//@SuppressLint("RememberReturnType")
//@Composable
//fun ShowSwitch(){
//    val isChecked = remember{
//        mutableStateOf(true)
//    }
//    Switch(
//        checked = isChecked.value,
//        onCheckedChange = {
//        isChecked.value = it
//    },
//    modifier = Modifier.run {
//        size(20.dp)
//        padding(100.dp)
//    })
//}
//
////MyApp2
////States in Compose
//@Composable
//fun MyApp2(modifier: Modifier = Modifier, names: List<String> = listOf("World", "Compose")){
//    Surface(modifier = Modifier.background(white)){
//        Column(modifier = Modifier.padding(8.dp)) {
//            //Greeting2(name = "Muhammad Waqas")
//            //Greeting2(name = "Muhammad Waqas")
//            for(name in names){
//                Greeting2(name = name)
//            }
//        }
//    }
//}
//@SuppressLint("UnrememberedMutableState")
//@Composable
//fun Greeting2(name:String){
//    //val expended = mutableStateOf(false)
//    var expended by remember {
//        mutableStateOf(false)
//    }
//    val extraPadding by animateDpAsState(
//        targetValue = if(expended) 50.dp else 0.dp,
//        animationSpec = tween(
//            durationMillis = 2000
//        )
//    )
//    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp) ){
//        Row(modifier = Modifier.padding(24.dp)){
//            Column(modifier = Modifier
//                .weight(1f)
//                .padding(bottom = extraPadding)) {
//                Text(text = "Hello, ")
//                Text(text = name)
////                if(expended.value){
////                    Text(text = "$name Extra Padding")
////                }else{
////                    Text(text = name)
////                }
//            }
//            OutlinedButton(onClick = { expended = !expended}) {
//                Text( if(expended) "Show less" else "Show More" , color = white)
//            }
//        }
//    }
//}
//
//@Composable
//fun CheckScreenActive(){
//    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true)}
//    if(shouldShowOnBoarding){
//        OnBoardingScreen(onContinueClicked = {
//            shouldShowOnBoarding=false
//        })
//    }else{
//        //Greeting2("Waqas")
//        //MyApp3()
//        MyApp2()
//    }
//}
////OnBoarding Screen
//@Composable
//fun OnBoardingScreen(onContinueClicked:() -> Unit){
//    Surface{
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ){
//            Text("Welcome to basics compose classes")
//            Button(
//                modifier = Modifier.padding(vertical = 24.dp),
//                onClick = onContinueClicked) {
//                Text(text = "Conitue Learning")
//            }
//        }
//    }
//}
//
////List view
//@Composable
//fun MyApp3(modifier: Modifier = Modifier, names: List<String> = List(1000){"$it"}){
//    Surface(modifier = Modifier.background(white)){
//        Column(modifier = Modifier.padding(8.dp)) {
//            LazyColumn {
//                item{Text("Header")}
//                items(names) { name ->
//                    ListView(name)
//                }
//            }
//        }
//    }
//}
//@Composable
//fun ListView(name:String){
//    //val expended = mutableStateOf(false)
//    val expended = remember {
//        mutableStateOf(false)
//    }
//    val extraPadding = if(expended.value) 50.dp else 0.dp
//    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp) ){
//        Row(modifier = Modifier.padding(24.dp)){
//            Column(modifier = Modifier
//                .weight(1f)
//                .padding(bottom = extraPadding)) {
//                Text(text = "Hello, ")
//                Text(text = name)
////                if(expended.value){
////                    Text(text = "$name Extra Padding")
////                }else{
////                    Text(text = name)
////                }
//            }
//            OutlinedButton(onClick = { expended.value = !expended.value }) {
//                Text( if(expended.value) "Show less" else "Show More" , color = white)
//            }
//        }
//    }
//}

//@Preview(showBackground = true, widthDp = 320, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview(showBackground = true, widthDp = 320)
//@Composable
//fun GreetingPreview() {
//    ComposeApplicatiomTheme {
//        //MyApp2()
//        //OnBoardingScreen(onContinueClicked = {})
//        //MyApp3()
//    }
//}
