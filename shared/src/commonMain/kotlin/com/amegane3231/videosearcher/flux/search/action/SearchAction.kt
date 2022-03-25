package com.amegane3231.videosearcher.flux.search.action

import com.amegane3231.videosearcher.data.video.common.CommonVideoResource
import com.amegane3231.videosearcher.flux.core.Action

sealed class SearchAction : Action {
    class Standby : SearchAction()
    class FetchDataSucceeded(val data: CommonVideoResource) : SearchAction()
    class FetchDataWaiting : SearchAction()
    class FetchDataFailed(val throwable: Throwable) : SearchAction()
}
