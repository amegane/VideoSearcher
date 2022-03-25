package com.amegane3231.videosearcher.flux.search.store

import com.amegane3231.videosearcher.data.video.common.CommonVideoDetail
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.core.Store
import com.amegane3231.videosearcher.flux.search.action.GetVideoDataAction
import com.amegane3231.videosearcher.flux.search.action.SearchAction
import kotlinx.coroutines.flow.StateFlow

interface SearchStore : Store {
    val dispatcher: Dispatcher

    val videoList: StateFlow<List<CommonVideoDetail>>

    val youtubePageToken: StateFlow<String>

    val searchState: StateFlow<SearchAction>

    val selectedVideoDetail: StateFlow<GetVideoDataAction>
}
