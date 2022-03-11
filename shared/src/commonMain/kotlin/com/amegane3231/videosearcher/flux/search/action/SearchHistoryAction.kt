package com.amegane3231.videosearcher.flux.search.action

import com.amegane3231.videosearcher.data.search.SearchHistory
import com.amegane3231.videosearcher.flux.core.Action

sealed class SearchHistoryAction : Action {
    class InsertSearchHistory(val query: String) : SearchHistoryAction()
    class GetSearchHistoryList(val list: List<SearchHistory>) : SearchHistoryAction()
}
