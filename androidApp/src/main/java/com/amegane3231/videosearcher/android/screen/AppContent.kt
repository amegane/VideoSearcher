package com.amegane3231.videosearcher.android.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amegane3231.videosearcher.android.theme.MovieSearcherTheme
import com.google.accompanist.insets.ProvideWindowInsets

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideoSearcherContent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen {
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
