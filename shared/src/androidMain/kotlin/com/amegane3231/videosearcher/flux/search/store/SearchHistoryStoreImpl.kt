package com.amegane3231.videosearcher.flux.search.store

import androidx.lifecycle.ViewModel
import com.amegane3231.videosearcher.data.search.SearchHistory
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.search.action.SearchHistoryAction
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

actual class SearchHistoryStoreImpl :
    SearchHistoryStore,
    ViewModel(),
    CoroutineScope,
    KoinComponent {
    actual override val dispatcher: Dispatcher by inject()

    private val _searchHistoryList: MutableStateFlow<List<SearchHistory>> =
        MutableStateFlow(listOf())

    actual override val searchHistoryList: StateFlow<List<SearchHistory>> = _searchHistoryList

    override val coroutineContext: CoroutineContext = Dispatchers.IO + Job()

    init {
        subscribe()
    }

    private fun subscribe() {
        Dispatcher.on(SearchHistoryAction.GetSearchHistoryList::class).subscribe(
            isThreadLocal = true,
            onNext = {
                launch {
                    _searchHistoryList.emit(it.list)
                }
            },
            onComplete = { }
        )
    }
}
