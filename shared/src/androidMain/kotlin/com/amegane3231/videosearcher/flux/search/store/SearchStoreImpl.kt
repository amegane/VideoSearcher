package com.amegane3231.videosearcher.flux.search.store

import androidx.lifecycle.ViewModel
import com.amegane3231.videosearcher.data.video.common.CommonVideoDetail
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

    private val _videoList: MutableStateFlow<List<CommonVideoDetail>> =
        MutableStateFlow(listOf())

    actual override val videoList: StateFlow<List<CommonVideoDetail>> = _videoList

    private val _youtubePageToken: MutableStateFlow<String> = MutableStateFlow("")

    actual override val youtubePageToken: StateFlow<String> = _youtubePageToken

    private val _searchState: MutableStateFlow<SearchAction> =
        MutableStateFlow(SearchAction.Standby())

    actual override val searchState: StateFlow<SearchAction> = _searchState

    private val _selectedVideoDetail: MutableStateFlow<GetVideoDataAction> =
        MutableStateFlow(GetVideoDataAction.Standby())

    actual override val selectedVideoDetail: StateFlow<GetVideoDataAction> =
        _selectedVideoDetail

    override val coroutineContext: CoroutineContext = Dispatchers.IO + Job()

    init {
        subscribeSearchState()
        subscribeClearVideoList()
        subscribeSelectedVideoData()
    }

    private fun subscribeSearchState() {
        dispatcher.on(SearchAction::class)
            .subscribe(
                isThreadLocal = true,
                onNext = {
                    launch {
                        _searchState.emit(it)
                        if (it is SearchAction.FetchDataSucceeded) {
                            val current = _videoList.value
                            _videoList.emit(current + it.data.videoDetailList)
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
                        _videoList.emit(listOf())
                    }
                }
            )
    }

    private fun subscribeSelectedVideoData() {
        dispatcher.on(GetVideoDataAction::class)
            .subscribe(
                isThreadLocal = true,
                onNext = {
                    launch {
                        _selectedVideoDetail.emit(it)
                    }
                }
            )
    }
}
