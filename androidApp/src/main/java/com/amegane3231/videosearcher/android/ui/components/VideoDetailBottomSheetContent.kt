package com.amegane3231.videosearcher.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amegane3231.videosearcher.android.R
import com.amegane3231.videosearcher.android.ui.theme.White
import com.amegane3231.videosearcher.flux.search.action.GetVideoDataAction
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalMaterialApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun VideoDetailBottomSheetContent(getVideoDataState: GetVideoDataAction) {
    when (getVideoDataState) {
        is GetVideoDataAction.FetchDataFailed -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = stringResource(R.string.msg_failed_data), textAlign = TextAlign.Center)
            }
        }
        is GetVideoDataAction.FetchDataWaiting, is GetVideoDataAction.Standby -> {
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
        is GetVideoDataAction.FetchDataSucceeded -> {
            val videoDetail = getVideoDataState.data.videoDetailList[0]
            VideoDetailBottomSheet(
                videoId = videoDetail.videoId,
                title = videoDetail.title,
                description = videoDetail.description,
                imageUrl = videoDetail.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(White)
            )
        }
    }
}
