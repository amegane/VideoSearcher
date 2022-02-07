package com.amegane3231.videosearcher.flux.search

import com.amegane3231.videosearcher.data.search.SearchResult
import com.amegane3231.videosearcher.flux.core.Action

sealed class SearchAction : Action {
    class Standby : SearchAction()
    class FetchDataSucceeded(val data: SearchResult) : SearchAction()
    class FetchDataWaiting : SearchAction()
    class FetchDataFailed(val throwable: Throwable) : SearchAction()
}
