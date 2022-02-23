package com.amegane3231.videosearcher.flux.search

import com.amegane3231.videosearcher.flux.core.Dispatcher
import kotlinx.coroutines.flow.StateFlow

expect class SearchStoreImpl : SearchStore {
    override val dispatcher: Dispatcher

    override val youtubeData: StateFlow<SearchAction>
}
