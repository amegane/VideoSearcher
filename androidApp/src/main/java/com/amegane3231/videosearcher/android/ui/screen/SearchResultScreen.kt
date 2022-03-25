package com.amegane3231.videosearcher.android.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.amegane3231.videosearcher.android.R
import com.amegane3231.videosearcher.android.ui.components.VideoDetailContent
import com.amegane3231.videosearcher.di.getKoinInstance
import com.amegane3231.videosearcher.flux.search.action.SearchAction
import com.amegane3231.videosearcher.flux.search.action.SearchActionCreator
import com.amegane3231.videosearcher.flux.search.store.SearchStore
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchResultScreen(store: SearchStore, query: String) {
    val videoList by store.videoList.collectAsState()

    val youtubePageToken by store.youtubePageToken.collectAsState()

    val searchState by store.searchState.collectAsState()

    val selectedVideoDetail by store.selectedVideoDetail.collectAsState()

    val searchActionCreator: SearchActionCreator = getKoinInstance()

    val resultLimit = 50

    when (searchState) {
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
            if (videoList.isNotEmpty()) {
                VideoDetailContent(
                    videoList = videoList,
                    videoDetail = selectedVideoDetail
                ) {
                    if (videoList.size < resultLimit) {
                        searchActionCreator.searchData(query, youtubePageToken)
                    }
                }
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
        is SearchAction.FetchDataSucceeded -> {
            VideoDetailContent(
                videoList = videoList,
                videoDetail = selectedVideoDetail
            ) {
                if (videoList.size < resultLimit) {
                    searchActionCreator.searchData(query, youtubePageToken)
                }
            }
        }
        is SearchAction.Standby -> {
        }
    }
}
