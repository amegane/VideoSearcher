package com.amegane3231.videosearcher.flux.search.action

import com.amegane3231.videosearcher.data.youtube.YoutubeSearchedData
import com.amegane3231.videosearcher.flux.core.Action

sealed class GetVideoDataAction : Action {
    class Standby : GetVideoDataAction()
    class FetchYoutubeDataSucceeded(val data: YoutubeSearchedData) : GetVideoDataAction()
    class FetchDataWaiting : GetVideoDataAction()
    class FetchDataFailed(val throwable: Throwable) : GetVideoDataAction()
}
