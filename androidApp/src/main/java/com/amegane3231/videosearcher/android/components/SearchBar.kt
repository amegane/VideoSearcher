package com.amegane3231.videosearcher.android.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.amegane3231.videosearcher.android.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    searchWords: String,
    onWordsChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row {
        TextField(
            value = searchWords,
            onValueChange = onWordsChange,
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
                onSearch(searchWords)
                keyboardController?.hide()
            }),
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
            modifier = modifier
        )
    }
}
