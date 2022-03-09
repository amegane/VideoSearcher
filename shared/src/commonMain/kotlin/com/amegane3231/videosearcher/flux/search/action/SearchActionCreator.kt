package com.amegane3231.videosearcher.flux.search.action

interface SearchActionCreator {
    fun searchData(query: String, pageToken: String = "")

    fun clearSearchData()
}
