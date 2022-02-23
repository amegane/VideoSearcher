package com.amegane3231.videosearcher.flux.search

import com.amegane3231.videosearcher.data.search.SearchHistory
import com.amegane3231.videosearcher.flux.core.Dispatcher
import kotlinx.coroutines.flow.StateFlow

expect class SearchHistoryStoreImpl : SearchHistoryStore {
    override val dispatcher: Dispatcher

    override val searchHistoryList: StateFlow<List<SearchHistory>>
}
