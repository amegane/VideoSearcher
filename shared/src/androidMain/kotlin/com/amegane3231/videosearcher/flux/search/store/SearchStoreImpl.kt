package com.amegane3231.videosearcher.flux.search.store

import androidx.lifecycle.ViewModel
import com.amegane3231.videosearcher.data.video.youtube.YoutubeVideoResource
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.search.action.ClearAction
import com.amegane3231.videosearcher.flux.search.action.GetVideoDataAction
import com.amegane3231.videosearcher.flux.search.action.SearchAction
import com.badoo.reaktive.observable.subscribe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

actual class SearchStoreImpl : SearchStore, ViewModel(), CoroutineScope, KoinComponent {
    actual override val dispatcher: Dispatcher by inject()

    private val _youtubeData: MutableStateFlow<List<YoutubeVideoResource>> =
        MutableStateFlow(listOf())

    actual override val youtubeData: StateFlow<List<YoutubeVideoResource>> = _youtubeData

    private val _youtubePageToken: MutableStateFlow<String> = MutableStateFlow("")

    actual override val youtubePageToken: StateFlow<String> = _youtubePageToken

    private val _youtubeSearchState: MutableStateFlow<SearchAction> =
        MutableStateFlow(SearchAction.Standby())

    actual override val youtubeSearchState: StateFlow<SearchAction> = _youtubeSearchState

    private val _selectedYoutubeVideoDetail: MutableStateFlow<GetVideoDataAction> =
        MutableStateFlow(GetVideoDataAction.Standby())

    actual override val selectedYoutubeVideoDetail: StateFlow<GetVideoDataAction> =
        _selectedYoutubeVideoDetail

    override val coroutineContext: CoroutineContext = Dispatchers.IO + Job()

    init {
        subscribeYoutubeSearchState()
        subscribeClearVideoList()
        subscribeSelectedYoutubeData()
    }

    private fun subscribeYoutubeSearchState() {
        dispatcher.on(SearchAction::class)
            .subscribe(
                isThreadLocal = true,
                onNext = {
                    launch {
                        _youtubeSearchState.emit(it)
                        if (it is SearchAction.FetchYoutubeDataSucceeded) {
                            val current = _youtubeData.value
                            _youtubeData.emit(current + it.data.items)
                            _youtubePageToken.emit(it.data.nextPageToken)
                        }
                    }
                }
            )
    }

    private fun subscribeClearVideoList() {
        dispatcher.on(ClearAction.ClearVideoList::class)
            .subscribe(
                isThreadLocal = true,
                onNext = {
                    launch {
                        _youtubeData.emit(listOf())
                    }
                }
            )
    }

    private fun subscribeSelectedYoutubeData() {
        dispatcher.on(GetVideoDataAction::class)
            .subscribe(
                isThreadLocal = true,
                onNext = {
                    launch {
                        _selectedYoutubeVideoDetail.emit(it)
                    }
                }
            )
    }
}
