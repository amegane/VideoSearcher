package com.amegane3231.videosearcher.android.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amegane3231.videosearcher.data.video.youtube.YoutubeVideoResource
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

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(12.dp),
        sheetContent = {
            if (selectedIndex >= 0) {
                VideoDetailBottomSheetContent(videoDetail)
            }
            Spacer(modifier = Modifier.height(1.dp))
        }
    ) {
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
