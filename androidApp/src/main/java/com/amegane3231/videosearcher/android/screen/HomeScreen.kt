package com.amegane3231.videosearcher.android.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amegane3231.videosearcher.android.components.SearchBar
import com.google.accompanist.insets.LocalWindowInsets

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(onSearch: () -> Unit) {
    val configuration = LocalConfiguration.current
    val defaultSearchBarHeight = (configuration.screenHeightDp / 2).dp
    val ime = LocalWindowInsets.current.ime
    val paddingTop: Dp by animateDpAsState(
        targetValue = if (ime.isVisible) {
            TextFieldDefaults.MinHeight
        } else {
            defaultSearchBarHeight
        }
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingTop)
    ) {
        SearchBar(onSearch)
    }
}
