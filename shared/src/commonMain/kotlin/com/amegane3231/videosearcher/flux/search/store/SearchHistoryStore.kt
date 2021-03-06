package com.amegane3231.videosearcher.flux.search.store

import com.amegane3231.videosearcher.data.history.SearchHistory
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.core.Store
import kotlinx.coroutines.flow.StateFlow

interface SearchHistoryStore : Store {
    val dispatcher: Dispatcher

    val searchHistoryList: StateFlow<List<SearchHistory>>
}
