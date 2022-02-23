package com.amegane3231.videosearcher.flux.search

import com.amegane3231.videosearcher.data.search.SearchResult
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.usecase.InsertSearchHistoryUseCase
import com.amegane3231.videosearcher.usecase.SearchYoutubeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class SearchActionCreatorImpl : SearchActionCreator, CoroutineScope, KoinComponent {
    private val dispatcher: Dispatcher by inject()

    private val searchYoutubeUseCase: SearchYoutubeUseCase by inject()

    private val insertSearchHistoryUseCase: InsertSearchHistoryUseCase by inject()

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun searchData(query: String) {
        dispatcher.dispatch(SearchAction.FetchDataWaiting())
        launch {
            insertSearchHistoryUseCase(query)
            searchYoutubeUseCase(query)
                .catch {
                    dispatcher.dispatch(SearchAction.FetchDataFailed(it))
                }
                .collect {
                    dispatcher.dispatch(SearchAction.FetchDataSucceeded(SearchResult.YoutubeData(it)))
                }
        }
    }
}