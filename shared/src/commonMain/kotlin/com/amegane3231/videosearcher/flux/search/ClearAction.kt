package com.amegane3231.videosearcher.flux.search

import com.amegane3231.videosearcher.flux.core.Action

sealed class ClearAction : Action {
    class ClearVideoList() : ClearAction()
}
