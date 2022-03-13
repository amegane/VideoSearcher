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
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideoDetailContent(searchResult: List<YoutubeVideoResource>, onAppearLastItem: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val scope = rememberCoroutineScope()

    var selectedIndex by remember { mutableStateOf(-1) }

    ModalBottomSheetLayout(sheetState = sheetState, sheetContent = {
        if (selectedIndex >= 0) {
            val videoDetail = searchResult[selectedIndex]
            VideoDetailBottomSheetContent(
                videoId = videoDetail.id.videoId,
                title = videoDetail.snippet.title,
                detail = videoDetail.snippet.description,
                imageUrl = videoDetail.snippet.thumbnails.high.url
            )
        }
        Text(text = "")
    }) {
        VideoListColumn(
            searchResult = searchResult,
            onClick = {
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
