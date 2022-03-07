package com.amegane3231.videosearcher.flux.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amegane3231.videosearcher.data.youtube.YoutubeVideoResource
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.badoo.reaktive.observable.subscribe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
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

    private val _youtubeError: MutableSharedFlow<Throwable> = MutableSharedFlow()

    actual override val youtubeError: SharedFlow<Throwable> = _youtubeError

    override val coroutineContext: CoroutineContext = Dispatchers.IO + Job()

    init {
        subscribeYoutubeData()
        subscribeYoutubeError()
        youtubeError.launchIn(viewModelScope)
    }

    private fun subscribeYoutubeData() {
        dispatcher.on(SearchAction.FetchYoutubeDataSucceeded::class)
            .subscribe(
                isThreadLocal = true,
                onNext = {
                    launch {
                        val current = _youtubeData.value
                        _youtubeData.emit(current + it.data.items)
                        _youtubePageToken.emit(it.data.nextPageToken)
                    }
                }
            )
    }

    private fun subscribeYoutubeError() {
        dispatcher.on(SearchAction.FetchDataFailed::class)
            .subscribe(
                isThreadLocal = true,
                onNext = {
                    launch {
                        _youtubeError.emit(it.throwable)
                    }
                }
            )
    }
}
