package com.amegane3231.videosearcher.flux.search.action

import android.util.Log
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.usecase.GetYoutubeVideoDataUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class GetVideoDataActionCreatorImpl : GetVideoDataActionCreator, CoroutineScope, KoinComponent {
    private val dispatcher: Dispatcher by inject()

    private val getYoutubeVideoDataUseCase: GetYoutubeVideoDataUseCase by inject()

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun getYoutubeVideoData(videoId: String) {
        dispatcher.dispatch(GetVideoDataAction.FetchDataWaiting())
        launch {
            getYoutubeVideoDataUseCase(videoId)
                .catch {
                    Log.e("Exception", it.message.toString())
                    dispatcher.dispatch(GetVideoDataAction.FetchDataFailed(it))
                }
                .collect {
                    dispatcher.dispatch(GetVideoDataAction.FetchYoutubeDataSucceeded(it))
                }
        }
    }
}
