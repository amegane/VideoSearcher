package com.amegane3231.videosearcher.di

import com.amegane3231.videosearcher.api.YoutubeAPI
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.search.SearchActionCreator
import com.amegane3231.videosearcher.flux.search.SearchStore
import com.amegane3231.videosearcher.repository.VideoSearchRepository
import com.amegane3231.videosearcher.usecase.SearchYoutubeUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule)
}

val commonModule = module {
    single { VideoSearchRepository() }
    single { YoutubeAPI() }
    single { Dispatcher }
    factory { SearchActionCreator() }
    factory { SearchStore() }
    factory { SearchYoutubeUseCase() }
}