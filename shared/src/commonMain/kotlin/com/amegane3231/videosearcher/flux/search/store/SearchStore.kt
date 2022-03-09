package com.amegane3231.videosearcher.flux.search.store

import com.amegane3231.videosearcher.data.youtube.YoutubeVideoResource
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.core.Store
import com.amegane3231.videosearcher.flux.search.action.SearchAction
import kotlinx.coroutines.flow.StateFlow

interface SearchStore : Store {
    val dispatcher: Dispatcher

    val youtubeData: StateFlow<List<YoutubeVideoResource>>

    val youtubePageToken: StateFlow<String>

    val youtubeSearchState: StateFlow<SearchAction>
}