package com.amegane3231.videosearcher.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amegane3231.videosearcher.data.search.SearchResult

@Composable
fun VideoListColumn(data: SearchResult, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        when (data) {
            is SearchResult.YoutubeData -> {
                items(data.data.items) {
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
    }
}
