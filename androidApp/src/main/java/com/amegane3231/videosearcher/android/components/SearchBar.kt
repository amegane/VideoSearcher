package com.amegane3231.videosearcher.android.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.amegane3231.videosearcher.android.R
import com.amegane3231.videosearcher.flux.search.SearchHistoryActionCreator

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(onSearch: () -> Unit) {
    var currentQuery by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val actionCreator = SearchHistoryActionCreator()

    if (currentQuery.isBlank()) {
        actionCreator.getSearchHistory("")
    }

    Row {
        TextField(
            value = currentQuery,
            onValueChange = {
                currentQuery = it
                actionCreator.getSearchHistory(it)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                cursorColor = MaterialTheme.colors.onSurface,
                leadingIconColor = MaterialTheme.colors.onSurface,
                focusedIndicatorColor = MaterialTheme.colors.onSurface,
                unfocusedIndicatorColor = MaterialTheme.colors.onSurface,
            ),
            label = {
                Text(
                    text = stringResource(R.string.search_movie),
                    color = MaterialTheme.colors.onBackground
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
            ),
            leadingIcon = {
                Icon(Icons.Filled.Search, null)
            },
            keyboardActions = KeyboardActions(onSearch = {
                actionCreator.insertSearchHistory(currentQuery)
                onSearch()
                keyboardController?.hide()
            }),
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
            modifier = Modifier
                .fillMaxWidth(fraction = 1f)
                .padding(8.dp)
        )
    }
}
