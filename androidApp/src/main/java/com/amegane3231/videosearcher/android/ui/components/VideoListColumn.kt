package com.amegane3231.videosearcher.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.amegane3231.videosearcher.data.video.youtube.YoutubeVideoResource
import kotlinx.coroutines.flow.collectLatest

@Composable
fun VideoListColumn(
    searchResults: List<YoutubeVideoResource>,
    onClick: (Int) -> Unit,
    onAppearLastItem: () -> Unit,
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
                    currentOnAppearLastItem()
                }
            }
    }

    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        itemsIndexed(searchResults) { index, video ->
            VideoOverviewRow(
                imageUrl = video.snippet.thumbnails.medium.url,
                videoTitle = video.snippet.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick(index)
                    }
            )
            Divider(color = MaterialTheme.colors.onBackground.copy(alpha = 0.25F))
        }
    }
}
