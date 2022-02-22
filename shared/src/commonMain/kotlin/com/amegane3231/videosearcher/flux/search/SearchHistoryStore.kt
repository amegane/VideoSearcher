package com.amegane3231.videosearcher.flux.search

import com.amegane3231.videosearcher.data.search.SearchHistory
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.core.Store
import kotlinx.coroutines.flow.StateFlow

expect class SearchHistoryStore : Store {
    val dispatcher: Dispatcher

    val searchHistoryList: StateFlow<List<SearchHistory>>
}
