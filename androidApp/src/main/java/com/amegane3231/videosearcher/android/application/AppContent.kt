package com.amegane3231.videosearcher.android.application

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amegane3231.videosearcher.android.screen.HomeScreen
import com.amegane3231.videosearcher.android.screen.Screen
import com.amegane3231.videosearcher.android.screen.SearchResultScreen
import com.amegane3231.videosearcher.android.theme.MovieSearcherTheme
import com.amegane3231.videosearcher.di.getKoinInstance
import com.amegane3231.videosearcher.flux.search.SearchHistoryStore
import com.amegane3231.videosearcher.flux.search.SearchStore
import com.google.accompanist.insets.ProvideWindowInsets

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideoSearcherContent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            val searchHistoryStore = getKoinInstance<SearchHistoryStore>()
            HomeScreen(searchHistoryStore, navController)
        }
        composable(
            "${Screen.SearchResult.route}/{query}",
            listOf(navArgument("query") { type = NavType.StringType })
        ) {
            val searchStore = getKoinInstance<SearchStore>()
            val query = requireNotNull(it.arguments?.getString("query"))
            SearchResultScreen(searchStore, query)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideoSearcher() {
    MovieSearcherTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                VideoSearcherContent()
            }
        }
    }
}
