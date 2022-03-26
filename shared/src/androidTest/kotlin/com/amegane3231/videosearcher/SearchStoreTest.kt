package com.amegane3231.videosearcher

import com.amegane3231.videosearcher.data.video.common.CommonVideoDetail
import com.amegane3231.videosearcher.data.video.common.CommonVideoResource
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.search.action.SearchAction
import com.amegane3231.videosearcher.flux.search.action.SearchActionCreator
import com.amegane3231.videosearcher.flux.search.store.SearchStore
import com.badoo.reaktive.observable.subscribe
import io.ktor.util.reflect.instanceOf
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.junit.Test
import kotlin.test.assertTrue

class SearchStoreTest {
    @Test
    fun getSearchDataSucceeded() {
        mockkObject(Dispatcher)

        val creator = mockk<SearchActionCreator>()
        every { creator.dispatcher } answers { Dispatcher }
        every { creator.searchData(any(), any()) } answers {
            creator.dispatcher.dispatch(
                SearchAction.FetchDataSucceeded(
                    CommonVideoResource(
                        videoDetailList = listOf(
                            CommonVideoDetail(videoId = "testId", title = "TEST")
                        )
                    )
                )
            )
        }

        val store = mockk<SearchStore>()
        every { store.dispatcher } answers { Dispatcher }

        val _searchState = MutableStateFlow<SearchAction>(SearchAction.Standby())
        every { store.searchState } answers { _searchState }

        store.dispatcher.on(SearchAction::class)
            .subscribe(
                isThreadLocal = true,
                onNext = {
                    io {
                        _searchState.emit(it)
                    }
                }
            )

        creator.searchData("TEST", "")
        verify(exactly = 1) { creator.searchData("TEST", "") }
        assertTrue { store.searchState.value.instanceOf(SearchAction.FetchDataSucceeded::class) }
    }

    @Test
    fun getSearchDataFailed() {
        mockkObject(Dispatcher)

        val creator = mockk<SearchActionCreator>()
        every { creator.dispatcher } answers { Dispatcher }
        every { creator.searchData(any(), any()) } answers {
            creator.dispatcher.dispatch(
                SearchAction.FetchDataFailed(Throwable())
            )
        }

        val store = mockk<SearchStore>()
        every { store.dispatcher } answers { Dispatcher }


        val _searchState = MutableStateFlow<SearchAction>(SearchAction.Standby())
        every { store.searchState } answers { _searchState }

        store.dispatcher.on(SearchAction::class)
            .subscribe(
                isThreadLocal = true,
                onNext = {
                    io {
                        _searchState.emit(it)
                    }
                }
            )

        creator.searchData("TEST", "")
        verify(exactly = 1) { creator.searchData("TEST", "") }
        assertTrue { store.searchState.value.instanceOf(SearchAction.FetchDataFailed::class) }
    }

    @Test
    fun getSearchDataLoading() {
        mockkObject(Dispatcher)

        val creator = mockk<SearchActionCreator>()
        every { creator.dispatcher } answers { Dispatcher }
        every { creator.searchData(any(), any()) } answers {
            creator.dispatcher.dispatch(
                SearchAction.FetchDataWaiting()
            )
            io {
                val job = io {
                    delay(3000)
                    // This function will not be called before assertTrue
                    creator.dispatcher.dispatch(
                        SearchAction.FetchDataFailed(Throwable())
                    )
                }
                job.join()
            }
        }

        val store = mockk<SearchStore>()
        every { store.dispatcher } answers { Dispatcher }


        val _searchState = MutableStateFlow<SearchAction>(SearchAction.Standby())
        every { store.searchState } answers { _searchState }

        store.dispatcher.on(SearchAction::class)
            .subscribe(
                isThreadLocal = true,
                onNext = {
                    io {
                        _searchState.emit(it)
                    }
                }
            )

        creator.searchData("TEST", "")
        verify(exactly = 1) { creator.searchData("TEST", "") }
        assertTrue { store.searchState.value.instanceOf(SearchAction.FetchDataWaiting::class) }
    }

    @Test
    fun getSearchDataStandby() {
        mockkObject(Dispatcher)

        val creator = mockk<SearchActionCreator>()
        every { creator.dispatcher } answers { Dispatcher }

        val store = mockk<SearchStore>()
        every { store.dispatcher } answers { Dispatcher }

        val _searchState = MutableStateFlow<SearchAction>(SearchAction.Standby())
        every { store.searchState } answers { _searchState }

        assertTrue { store.searchState.value.instanceOf(SearchAction.Standby::class) }
    }

    private fun io(block: suspend CoroutineScope.() -> Unit) =
        CoroutineScope(Dispatchers.IO).launch(block = block)
}
