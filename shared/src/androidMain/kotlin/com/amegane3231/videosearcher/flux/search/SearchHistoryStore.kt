package com.amegane3231.videosearcher.flux.search

import androidx.lifecycle.ViewModel
import com.amegane3231.videosearcher.data.search.SearchHistory
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

actual class SearchHistoryStore : Store, ViewModel(), CoroutineScope, KoinComponent {
    actual val dispatcher: Dispatcher by inject()

    private val _searchHistoryList: MutableStateFlow<List<SearchHistory>> =
        MutableStateFlow(listOf())

    actual val searchHistoryList: StateFlow<List<SearchHistory>> = _searchHistoryList

    override val coroutineContext: CoroutineContext = Dispatchers.IO + Job()

    init {
        subscribe()
    }

    private fun subscribe() {
        dispatcher.observer.subscribe(
            isThreadLocal = true,
            onNext = {
                if (it is SearchHistoryAction.GetSearchHistoryList) {
                    launch {
                        _searchHistoryList.emit(it.list)
                    }
                }
            },
            onComplete = { }
        )
    }
}
