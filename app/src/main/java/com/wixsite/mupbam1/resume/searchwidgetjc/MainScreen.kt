package com.wixsite.mupbam1.resume.searchwidgetjc

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import kotlin.random.Random

@Composable
fun MainScreen(mainViewModel:MainViewModel) {
    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState


    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    mainViewModel.updateSearchTextState(newValue = it)},
                onCloseClicked = {
                  //  mainViewModel.updateSearchTextState("")
                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                                 },
                onSearchClicked = {
                                  Log.d("MyLog",it)
                },
                onSearchTriggered = {
                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPEN)
                }
            )
        }
    ) {}
}

@Composable
fun MainAppBar( searchWidgetState: SearchWidgetState,
                searchTextState: String,
                onTextChange: (String) -> Unit,
                onCloseClicked: () -> Unit,
                onSearchClicked: (String) -> Unit,
                onSearchTriggered: () -> Unit) {
    when(searchWidgetState){
        SearchWidgetState.CLOSED->{
            DefaultAppBar(
                onSearchClicked=onSearchTriggered
            )
        }
        SearchWidgetState.OPEN->{
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun DefaultAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Home"
            )
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun SearchAppBar(
    text:String,
    onTextChange:(String)->Unit,
    onCloseClicked:()->Unit,
    onSearchClicked:(String)->Unit,
) {
    val focusRequest= remember {
        FocusRequester()
    }
    LaunchedEffect(key1 = Unit){
        focusRequest.requestFocus()
    }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ){
        TextField(modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequest),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(modifier = Modifier
                    .alpha(ContentAlpha.medium),
                    text = "Search here...",
                    color = Color.White)
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier
                    .alpha(ContentAlpha.medium),
                    onClick = {}) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White)
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        }
                        else {
                            onCloseClicked()
                        }
                    }) {
                    Icon(imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White)
                }

            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
    
}

@Composable
@Preview
fun DefaultAppBarPreview() {
    DefaultAppBar(onSearchClicked = {})
}

@Composable
@Preview
fun SearchAppBarPreview() {
    SearchAppBar(
        text = "SomeRNDText",
        onTextChange ={} ,
        onCloseClicked = { /*TODO*/ },
        onSearchClicked ={} )
}