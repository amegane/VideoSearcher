package com.amegane3231.videosearcher.android.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.amegane3231.videosearcher.data.youtube.YoutubeVideoResource
import com.amegane3231.videosearcher.di.getKoinInstance
import com.amegane3231.videosearcher.flux.search.action.GetVideoDataAction
import com.amegane3231.videosearcher.flux.search.action.GetVideoDataActionCreator
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideoDetailContent(
    searchResults: List<YoutubeVideoResource>,
    videoDetail: GetVideoDataAction,
    onAppearLastItem: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val scope = rememberCoroutineScope()

    var selectedIndex by remember { mutableStateOf(-1) }

    ModalBottomSheetLayout(sheetState = sheetState, sheetContent = {
        if (selectedIndex >= 0) {
            VideoDetailBottomSheetContent(videoDetail)
        }
        Text(text = "")
    }) {
        VideoListColumn(
            searchResults = searchResults,
            onClick = {
                val getVideoDataActionCreator: GetVideoDataActionCreator = getKoinInstance()
                getVideoDataActionCreator.getYoutubeVideoData(searchResults[it].id.videoId)
                selectedIndex = it
                scope.launch { sheetState.show() }
            },
            onAppearLastItem = onAppearLastItem,
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        )
    }
}
