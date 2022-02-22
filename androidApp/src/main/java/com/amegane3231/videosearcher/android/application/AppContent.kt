package com.amegane3231.videosearcher.android.application

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amegane3231.videosearcher.android.screen.HomeScreen
import com.amegane3231.videosearcher.android.screen.Screen
import com.amegane3231.videosearcher.android.screen.SearchResultScreen
import com.amegane3231.videosearcher.android.theme.MovieSearcherTheme
import com.amegane3231.videosearcher.flux.search.SearchHistoryStore
import com.google.accompanist.insets.ProvideWindowInsets

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideoSearcherContent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(SearchHistoryStore()) {
                navController.navigate(Screen.SearchResult.route)
            }
        }
        composable(Screen.SearchResult.route) {
            SearchResultScreen()
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideoSearcher() {
    MovieSearcherTheme {
        ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
            VideoSearcherContent()
        }
    }
}
