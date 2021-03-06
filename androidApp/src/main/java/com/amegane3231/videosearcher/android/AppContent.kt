package com.amegane3231.videosearcher.android

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
import com.amegane3231.videosearcher.android.ui.screen.HomeScreen
import com.amegane3231.videosearcher.android.ui.screen.Screen
import com.amegane3231.videosearcher.android.ui.screen.SearchResultScreen
import com.amegane3231.videosearcher.android.ui.theme.MovieSearcherTheme
import com.amegane3231.videosearcher.di.getKoinInstance
import com.amegane3231.videosearcher.flux.search.store.SearchHistoryStore
import com.amegane3231.videosearcher.flux.search.store.SearchStore
import com.google.accompanist.insets.ProvideWindowInsets

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideoSearcherContent() {
    val navController = rememberNavController()

    val searchStore = getKoinInstance<SearchStore>()

    val searchHistoryStore = getKoinInstance<SearchHistoryStore>()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(searchHistoryStore) {
                navController.navigate("${Screen.SearchResult.route}/$it")
            }
        }
        composable(
            "${Screen.SearchResult.route}/{query}",
            listOf(navArgument("query") { type = NavType.StringType })
        ) {
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
