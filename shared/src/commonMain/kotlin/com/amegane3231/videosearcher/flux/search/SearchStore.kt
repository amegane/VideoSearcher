package com.amegane3231.videosearcher.flux.search

import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.core.Store
import kotlinx.coroutines.flow.StateFlow

interface SearchStore : Store {
    val dispatcher: Dispatcher

    val youtubeData: StateFlow<SearchAction>
}