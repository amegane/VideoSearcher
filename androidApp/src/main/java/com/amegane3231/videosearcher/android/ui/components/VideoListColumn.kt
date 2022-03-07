package com.amegane3231.videosearcher.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amegane3231.videosearcher.data.youtube.YoutubeVideoResource
import kotlinx.coroutines.flow.collectLatest

@Composable
fun VideoListColumn(
    searchResult: List<YoutubeVideoResource>,
    onAppearLastItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    val isReachedToListEnd by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex + listState.layoutInfo
                .visibleItemsInfo.size == listState.layoutInfo.totalItemsCount
        }
    }

    val currentOnAppearLastItem by rememberUpdatedState(newValue = onAppearLastItem)
    LaunchedEffect(Unit) {
        snapshotFlow { isReachedToListEnd }
            .collectLatest { isReached ->
                if (isReached) {
                    currentOnAppearLastItem(listState.layoutInfo.totalItemsCount)
                }
            }
    }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        items(searchResult) {
            VideoOverviewRow(
                imageUrl = it.snippet.thumbnails.medium.url,
                videoTitle = it.snippet.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { }
            )
        }
    }
}
