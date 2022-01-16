package com.amegane3231.moviesearch.di

import com.amegane3231.moviesearch.api.YoutubeAPI
import com.amegane3231.moviesearch.flux.core.Dispatcher
import com.amegane3231.moviesearch.flux.search.SearchActionCreator
import com.amegane3231.moviesearch.flux.search.SearchStore
import com.amegane3231.moviesearch.repository.VideoSearchRepository
import com.amegane3231.moviesearch.usecase.SearchYoutubeUseCase
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