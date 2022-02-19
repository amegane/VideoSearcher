package com.amegane3231.videosearcher.flux.search

import com.amegane3231.videosearcher.flux.core.ActionCreator
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.usecase.GetSearchHistoriesUseCase
import com.amegane3231.videosearcher.usecase.InsertSearchHistoryUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class SearchHistoryActionCreator : ActionCreator, CoroutineScope, KoinComponent {
    private val dispatcher: Dispatcher by inject()

    private val insertSearchHistoryUseCase: InsertSearchHistoryUseCase by inject()

    private val getSearchHistoriesUseCase: GetSearchHistoriesUseCase by inject()

    override val coroutineContext: CoroutineContext = Dispatchers.IO + Job()

    fun insertSearchHistory(query: String) {
        launch {
            insertSearchHistoryUseCase.insert(query)
            dispatcher.dispatch(SearchHistoryAction.InsertSearchHistory(query))
        }
    }

    fun getSearchHistory(query: String) {
        launch {
            getSearchHistoriesUseCase.getSearchHistoryList(query).collect {
                dispatcher.dispatch(SearchHistoryAction.GetSearchHistoryList(it))
            }
        }
    }
}
