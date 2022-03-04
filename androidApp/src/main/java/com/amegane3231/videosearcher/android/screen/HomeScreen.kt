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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.amegane3231.videosearcher.android.components.SearchBar
import com.amegane3231.videosearcher.android.components.SearchHistoriesColumn
import com.amegane3231.videosearcher.di.getKoinInstance
import com.amegane3231.videosearcher.flux.search.SearchActionCreator
import com.amegane3231.videosearcher.flux.search.SearchHistoryActionCreator
import com.amegane3231.videosearcher.flux.search.SearchHistoryStore
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.imePadding

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(store: SearchHistoryStore, navigate: () -> Unit) {
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

    val searchActionCreator: SearchActionCreator = getKoinInstance()

    val searchHistoryActionCreator: SearchHistoryActionCreator = getKoinInstance()

    var currentQuery by remember { mutableStateOf("") }

    val isListEmpty = searchHistoryList.isEmpty()

    if (isListEmpty) {
        searchHistoryActionCreator.getSearchHistory(currentQuery)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingTop)
    ) {
        SearchBar(
            searchWords = currentQuery,
            onWordsChange = {
                currentQuery = it
                searchHistoryActionCreator.getSearchHistory(it)
            },
            onSearch = {
                if (it.isBlank()) return@SearchBar
                navigate()
                searchHistoryActionCreator.insertSearchHistory(it)
                searchActionCreator.searchData(it)
            },
            modifier = Modifier
                .fillMaxWidth(fraction = 1f)
                .padding(8.dp)
        )
        if (paddingTop.value == TextFieldDefaults.MinHeight.value) {
            SearchHistoriesColumn(
                searchHistoryList = searchHistoryList,
                onSearch = {
                    navigate()
                    searchActionCreator.searchData(it)
                },
                modifier = Modifier.imePadding()
            )
        }
    }
}
