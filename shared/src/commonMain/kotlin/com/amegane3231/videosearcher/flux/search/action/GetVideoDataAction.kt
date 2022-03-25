package com.amegane3231.videosearcher.flux.search.action

import com.amegane3231.videosearcher.data.video.common.CommonVideoResource
import com.amegane3231.videosearcher.flux.core.Action

sealed class GetVideoDataAction : Action {
    class Standby : GetVideoDataAction()
    class FetchDataSucceeded(val data: CommonVideoResource) : GetVideoDataAction()
    class FetchDataWaiting : GetVideoDataAction()
    class FetchDataFailed(val throwable: Throwable) : GetVideoDataAction()
}
