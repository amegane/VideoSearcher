package com.amegane3231.videosearcher.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amegane3231.videosearcher.android.R
import com.amegane3231.videosearcher.data.search.SearchHistory

@Composable
fun SearchHistoryRow(history: SearchHistory, onSearch: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onSearch()
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_history),
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(text = history.words)
        }
    }
}
