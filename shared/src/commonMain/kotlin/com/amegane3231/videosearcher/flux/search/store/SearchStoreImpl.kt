package com.amegane3231.videosearcher.flux.search.store

import com.amegane3231.videosearcher.data.youtube.YoutubeVideoResource
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.search.action.SearchAction
import kotlinx.coroutines.flow.StateFlow

expect class SearchStoreImpl : SearchStore {
    override val dispatcher: Dispatcher

    override val youtubeData: StateFlow<List<YoutubeVideoResource>>

    override val youtubePageToken: StateFlow<String>

    override val youtubeSearchState: StateFlow<SearchAction>
}