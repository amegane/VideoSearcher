package com.amegane3231.moviesearch.flux.search

import com.amegane3231.moviesearch.data.youtube.YoutubeSearchedData
import com.amegane3231.moviesearch.flux.core.Action

sealed class SearchAction : Action {
    class Standby : SearchAction()
    class FetchDataSucceeded(val data: YoutubeSearchedData) : SearchAction()
    class FetchDataWaiting : SearchAction()
    class FetchDataFailed(val throwable: Throwable) : SearchAction()
}