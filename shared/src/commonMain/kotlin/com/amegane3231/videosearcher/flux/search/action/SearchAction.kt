package com.amegane3231.videosearcher.flux.search.action

import com.amegane3231.videosearcher.data.video.youtube.YoutubeSearchedData
import com.amegane3231.videosearcher.flux.core.Action

sealed class SearchAction : Action {
    class Standby : SearchAction()
    class FetchYoutubeDataSucceeded(val data: YoutubeSearchedData) : SearchAction()
    class FetchDataWaiting : SearchAction()
    class FetchDataFailed(val throwable: Throwable) : SearchAction()
}
