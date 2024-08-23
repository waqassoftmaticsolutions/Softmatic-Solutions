@Composable

fun SearchBar(modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White, shape = RoundedCornerShape(28.dp))
            .padding(top=25.dp),
        placeholder = {
            Text(
                text = "Search",
                color = Color.Gray,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )

        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon",
                tint = Color.Gray
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(28.dp)
    )
}
