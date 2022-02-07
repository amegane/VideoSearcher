package com.amegane3231.videosearcher

import com.amegane3231.videosearcher.data.search.SearchResult
import com.amegane3231.videosearcher.data.youtube.*
import com.amegane3231.videosearcher.di.initKoin
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.search.SearchAction
import com.amegane3231.videosearcher.flux.search.SearchActionCreator
import com.amegane3231.videosearcher.flux.search.SearchStore
import io.ktor.util.reflect.*
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.spyk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class AndroidYoutubeAPITest {
    @Before
    fun init() {
        initKoin()
    }

    @Test
    fun getSearchData() {
        mockkObject(Dispatcher)

        val creator = spyk(SearchActionCreator())

        val store = spyk(SearchStore())

        every { creator.searchData(any()) } answers {
            Dispatcher.dispatch(SearchAction.FetchDataWaiting())

            val flow = flow<YoutubeSearchedData> {
                emit(
                    YoutubeSearchedData(
                        kind = "youtube # searchListResponse",
                        etag = "tK68xfV - tb9T5kVhWqeCtcbMhJc",
                        nextPageToken = "CAUQAA",
                        prevPageToken = "",
                        pageInfo = PageInfo(totalResults = 1000000, resultsPerPage = 5),
                        items = listOf(
                            YoutubeVideoResource(
                                kind = "youtube#searchResult",
                                etag =
                                "g0fssF3Jn85WQSf-SJpGbFe3Cd0",
                                id =
                                YoutubeVideoId(
                                    kind = "youtube#video",
                                    videoId = "zo04nV3HBEQ",
                                    channelId = "",
                                    playlistId = ""
                                ),
                                snippet = YoutubeVideoSnippet(
                                    publishedAt = "2021-04-21T09:31:38Z",
                                    channelId = "UC_f1D-UUq9QnvsMsVRVxVSA",
                                    title = "How to do a lateral flow device test",
                                    description = "Free, fast and regular testing for people who do not have symptoms of coronavirus( COVID - 19) is available to everyone in ...",
                                    thumbnails = Thumbnails(
                                        default = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/zo04nV3HBEQ/default.jpg",
                                            width = 120,
                                            height = 90
                                        ),
                                        medium = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/zo04nV3HBEQ/mqdefault.jpg",
                                            width = 320,
                                            height = 180
                                        ),
                                        high = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/zo04nV3HBEQ/hqdefault.jpg",
                                            width = 480,
                                            height = 360
                                        )
                                    ), channelTitle = "NHS inform"
                                )
                            ),
                            YoutubeVideoResource(
                                kind = "youtube#searchResult",
                                etag = "RcXKRiPuJVj9T9cCm4s-kROm5_g",
                                id = YoutubeVideoId(
                                    kind = "youtube#video",
                                    videoId = "S9XR8RZxKNo",
                                    channelId = "",
                                    playlistId = ""
                                ),
                                snippet = YoutubeVideoSnippet(
                                    publishedAt = "2021-03-01T14:46:46Z",
                                    channelId = "UCXmtnbNO7_no7RekfUIqVcw",
                                    title = "How to do a COVID -19 Self Test(rapid antigen test)",
                                    description = "Dr Amir Khan shows you how to test yourself for coronavirus(COVID - 19) using a self -test rapid antigen test kit, as well as how to ...",
                                    thumbnails = Thumbnails(
                                        default = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/S9XR8RZxKNo/default.jpg",
                                            width = 120,
                                            height = 90
                                        ),
                                        medium = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/S9XR8RZxKNo/mqdefault.jpg",
                                            width = 320,
                                            height = 180
                                        ),
                                        high = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/S9XR8RZxKNo/hqdefault.jpg",
                                            width = 480,
                                            height = 360
                                        )
                                    ), channelTitle = "Department of Health and Social Care"
                                )
                            ),
                            YoutubeVideoResource(
                                kind = "youtube#searchResult",
                                etag = "8NQCpEyc8GvuO27jVfak0b0zVHw",
                                id = YoutubeVideoId(
                                    kind = "youtube#video",
                                    videoId = "baQQfoX-JXo",
                                    channelId = "",
                                    playlistId = ""
                                ),
                                snippet = YoutubeVideoSnippet(
                                    publishedAt = "2021-04-19T19:08:17Z",
                                    channelId = "UC346EX3v26ACsjLhe-HwHDg",
                                    title = "HOW TO: A Guide for the BinaxNOW™ COVID-19 Self Test",
                                    description = "Finally: a fast, proven and trusted COVID-19 test that is readily available to the public. Simply purchase at a retail store near you ...",
                                    thumbnails = Thumbnails(
                                        default = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/baQQfoX-JXo/default.jpg",
                                            width = 120,
                                            height = 90
                                        ),
                                        medium = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/baQQfoX-JXo/mqdefault.jpg",
                                            width = 320,
                                            height = 180
                                        ),
                                        high = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/baQQfoX-JXo/hqdefault.jpg",
                                            width = 480,
                                            height = 360
                                        )
                                    ),
                                    channelTitle = "Abbott"
                                )
                            ),
                            YoutubeVideoResource(
                                kind = "youtube#searchResult",
                                etag = "NwQ2WCU9HfqgqyC6NhqRfuc9k_o",
                                id = YoutubeVideoId(
                                    kind = "youtube#video",
                                    videoId = "Rs3GfkHRwXA",
                                    channelId = "",
                                    playlistId = ""
                                ),
                                snippet = YoutubeVideoSnippet(
                                    publishedAt = "2021-09-02T11:58:26Z",
                                    channelId = "UCH7nv1A9xIrAifZJNvt7cgA",
                                    title = "ABP Majha LIVE : Virat Kohli Resigns From Test Captaincy | Maharashtra COVID-19 | Omicron | LIVE",
                                    description = "ABPMajhaLIVE #ViratKohli #India #CMUddhavThackeray #COVID19 #MakarSankranti2022 #MaharashtraLockdownUpdates ...",
                                    thumbnails = Thumbnails(
                                        default = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/Rs3GfkHRwXA/default_live.jpg",
                                            width = 120,
                                            height = 90
                                        ),
                                        medium = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/Rs3GfkHRwXA/mqdefault_live.jpg",
                                            width = 320,
                                            height = 180
                                        ),
                                        high = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/Rs3GfkHRwXA/hqdefault_live.jpg",
                                            width = 480,
                                            height = 360
                                        )
                                    ),
                                    channelTitle = "ABP MAJHA"
                                )
                            ),
                            YoutubeVideoResource(
                                kind = "youtube#searchResult",
                                etag = "LiknxTPZJkHA76PZVklJvq9GCHw",
                                id = YoutubeVideoId(
                                    kind = "youtube#video",
                                    videoId = "hU8F-mU9A8Q",
                                    channelId = "",
                                    playlistId = ""
                                ),
                                snippet = YoutubeVideoSnippet(
                                    publishedAt = "2022-01-13T18:29:32Z",
                                    channelId = "UCzdgWZfyWtkrlRUrLwWYfbw",
                                    title = "Mock Test - 4 | General Awareness For Mains | By Abhijeet Mishra | Bankers Way",
                                    description = "In this session, Abhijeet Mishra Sir will be discussing Mock Test from General Awareness section. Watch the entire video to learn ...",
                                    thumbnails = Thumbnails(
                                        default = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/hU8F-mU9A8Q/default_live.jpg",
                                            width = 120,
                                            height = 90
                                        ),
                                        medium = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/hU8F-mU9A8Q/mqdefault_live.jpg",
                                            width = 320,
                                            height = 180
                                        ),
                                        high = DetailThumbnail(
                                            url = "https://i.ytimg.com/vi/hU8F-mU9A8Q/hqdefault_live.jpg",
                                            width = 480,
                                            height = 360
                                        )
                                    ),
                                    channelTitle = "Bankers Way"
                                )
                            )
                        )
                    )
                )
            }
            io {
                flow.collect {
                    Dispatcher.dispatch(SearchAction.FetchDataSucceeded(SearchResult.YoutubeData(it)))
                }
            }
        }

        creator.searchData("TEST")
        assertTrue { store.youtubeData.value.instanceOf(SearchAction.FetchDataSucceeded::class) }
    }

    private fun io(block: suspend CoroutineScope.() -> Unit) =
        CoroutineScope(Dispatchers.IO).launch(block = block)
}