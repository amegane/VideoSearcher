package com.amegane3231.videosearcher.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun VideoOverviewRow(imageUrl: String, videoTitle: String, modifier: Modifier = Modifier) {
    val painter = rememberImagePainter(data = imageUrl)

    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        Row(modifier = modifier) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .width(128.dp)
                    .height(72.dp)
                    .padding(end = 12.dp)
            )
            Text(text = videoTitle, maxLines = 3, overflow = TextOverflow.Ellipsis)
        }
    }
}
