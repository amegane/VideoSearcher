package com.amegane3231.videosearcher.flux.search

import com.amegane3231.videosearcher.flux.core.ActionCreator

interface SearchHistoryActionCreator : ActionCreator {
    fun insertSearchHistory(query: String)

    fun getSearchHistory(query: String)
}
