package com.amegane3231.videosearcher.android.screen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object SearchResult : Screen("searchResult")
}
