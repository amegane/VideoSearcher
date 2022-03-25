package com.amegane3231.videosearcher.flux.search.action

import com.amegane3231.videosearcher.flux.core.Dispatcher

interface SearchActionCreator {
    val dispatcher: Dispatcher

    fun searchData(query: String, pageToken: String = "")

    fun clearSearchData()
}
