package com.amegane3231.videosearcher.flux.search.action

import com.amegane3231.videosearcher.flux.core.ActionCreator
import com.amegane3231.videosearcher.flux.core.Dispatcher

interface SearchHistoryActionCreator : ActionCreator {
    val dispatcher: Dispatcher

    fun insertSearchHistory(query: String)

    fun getSearchHistory(query: String)
}
