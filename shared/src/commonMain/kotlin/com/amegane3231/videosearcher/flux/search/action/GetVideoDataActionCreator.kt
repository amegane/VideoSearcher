package com.amegane3231.videosearcher.flux.search.action

import com.amegane3231.videosearcher.flux.core.ActionCreator

interface GetVideoDataActionCreator : ActionCreator {
    fun getYoutubeVideoData(videoId: String)
}
