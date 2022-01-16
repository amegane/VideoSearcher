package com.amegane3231.moviesearch.flux.search

import com.amegane3231.moviesearch.flux.core.Dispatcher
import com.amegane3231.moviesearch.flux.core.Store
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

expect class SearchStore : Store {
    val dispatcher: Dispatcher

    val _youtubeData: MutableStateFlow<SearchAction>

    val youtubeData: StateFlow<SearchAction>

    fun register(action: SearchAction)
}