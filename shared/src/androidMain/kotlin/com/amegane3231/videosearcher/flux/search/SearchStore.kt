package com.amegane3231.videosearcher.flux.search

import androidx.lifecycle.ViewModel
import com.amegane3231.videosearcher.data.search.SearchResult
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.core.Store
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

actual class SearchStore : Store, ViewModel(), CoroutineScope, KoinComponent {
    actual val dispatcher: Dispatcher by inject()

    private val _youtubeData: MutableStateFlow<SearchAction> =
        MutableStateFlow(SearchAction.Standby())

    actual val youtubeData: StateFlow<SearchAction> = _youtubeData

    override val coroutineContext: CoroutineContext = Dispatchers.IO + Job()

    init {
        subscribe()
    }

    private fun subscribe() {
        dispatcher.observer.subscribe(
            isThreadLocal = true,
            onNext = {
                if (it is SearchAction.FetchDataSucceeded && it.data is SearchResult.YoutubeData) {
                    launch {
                        _youtubeData.emit(it)
                    }
                }
            },
            onComplete = { }
        )
    }
}
