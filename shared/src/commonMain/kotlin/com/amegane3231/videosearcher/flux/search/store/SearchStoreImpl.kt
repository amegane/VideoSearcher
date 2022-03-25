package com.amegane3231.videosearcher.flux.search.store

import com.amegane3231.videosearcher.data.video.common.CommonVideoDetail
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.search.action.GetVideoDataAction
import com.amegane3231.videosearcher.flux.search.action.SearchAction
import kotlinx.coroutines.flow.StateFlow

expect class SearchStoreImpl : SearchStore {
    override val dispatcher: Dispatcher

    override val videoList: StateFlow<List<CommonVideoDetail>>

    override val youtubePageToken: StateFlow<String>

    override val searchState: StateFlow<SearchAction>

    override val selectedVideoDetail: StateFlow<GetVideoDataAction>
}
