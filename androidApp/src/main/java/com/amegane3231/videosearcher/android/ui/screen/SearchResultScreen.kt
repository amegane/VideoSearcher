package com.amegane3231.videosearcher.android.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.amegane3231.videosearcher.android.R
import com.amegane3231.videosearcher.android.ui.components.VideoListColumn
import com.amegane3231.videosearcher.di.getKoinInstance
import com.amegane3231.videosearcher.flux.search.SearchAction
import com.amegane3231.videosearcher.flux.search.SearchActionCreator
import com.amegane3231.videosearcher.flux.search.SearchStore
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun SearchResultScreen(store: SearchStore, query: String) {
    val youtubeData by store.youtubeData.collectAsState()

    val youtubePageToken by store.youtubePageToken.collectAsState()

    val youtubeSearchState by store.youtubeSearchState.collectAsState()

    val searchActionCreator: SearchActionCreator = getKoinInstance()

    val resultLimit = 50

    when (youtubeSearchState) {
        is SearchAction.FetchDataFailed -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = stringResource(R.string.msg_failed_data), textAlign = TextAlign.Center)
            }
        }
        is SearchAction.FetchDataWaiting -> {
            if (youtubeData.isNotEmpty()) {
                VideoListColumn(
                    searchResult = youtubeData,
                    onAppearLastItem = {
                        if (youtubeData.size < resultLimit) {
                            searchActionCreator.searchData(query, youtubePageToken)
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding()
                )
                return
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
            ) {
                CircularProgressIndicator()
            }
        }
        is SearchAction.FetchYoutubeDataSucceeded -> {
            VideoListColumn(
                searchResult = youtubeData,
                onAppearLastItem = {
                    if (youtubeData.size < resultLimit) {
                        searchActionCreator.searchData(query, youtubePageToken)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
            )
        }
        is SearchAction.Standby -> {
        }
    }
}
