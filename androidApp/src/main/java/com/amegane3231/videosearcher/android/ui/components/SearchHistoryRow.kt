package com.amegane3231.videosearcher.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amegane3231.videosearcher.android.R
import com.amegane3231.videosearcher.data.search.SearchHistory

@Composable
fun SearchHistoryRow(history: SearchHistory, modifier: Modifier = Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_history),
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(text = history.words, maxLines = 2)
        }
    }
}
