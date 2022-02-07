package com.amegane3231.videosearcher.flux.search

import com.amegane3231.videosearcher.data.search.SearchResult
import com.amegane3231.videosearcher.flux.core.ActionCreator
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.usecase.SearchYoutubeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class SearchActionCreator : ActionCreator, CoroutineScope, KoinComponent {
    private val dispatcher: Dispatcher by inject()

    private val searchYoutubeUseCase: SearchYoutubeUseCase by inject()

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    fun searchData(query: String) {
        dispatcher.dispatch(SearchAction.FetchDataWaiting())
        launch {
            searchYoutubeUseCase.searchYoutube(query)
                .catch {
                    println(it)
                    dispatcher.dispatch(SearchAction.FetchDataFailed(it))
                }
                .collect {
                    println(it)
                    dispatcher.dispatch(SearchAction.FetchDataSucceeded(SearchResult.YoutubeData(it)))
                }
        }
    }
}
