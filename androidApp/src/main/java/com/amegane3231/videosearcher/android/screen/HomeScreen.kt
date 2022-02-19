package com.amegane3231.videosearcher.android.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.amegane3231.videosearcher.android.components.SearchBar
import com.amegane3231.videosearcher.android.components.SearchHistoriesColumn
import com.amegane3231.videosearcher.flux.search.SearchHistoryStore
import com.google.accompanist.insets.LocalWindowInsets

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(store: SearchHistoryStore, onSearch: () -> Unit) {
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

    val searchHistoryList by store.searchHistoryList.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingTop)
    ) {
        SearchBar(onSearch)
        if (ime.isVisible) {
            SearchHistoriesColumn(searchHistoryList = searchHistoryList, onSearch = onSearch)
        }
    }
}
