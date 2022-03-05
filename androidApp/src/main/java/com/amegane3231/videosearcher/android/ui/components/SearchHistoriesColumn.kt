package com.amegane3231.videosearcher.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amegane3231.videosearcher.data.search.SearchHistory

@Composable
fun SearchHistoriesColumn(
    searchHistoryList: List<SearchHistory>,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(searchHistoryList) { history ->
            SearchHistoryRow(
                history = history,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSearch(history.words) }
            )
        }
    }
}
