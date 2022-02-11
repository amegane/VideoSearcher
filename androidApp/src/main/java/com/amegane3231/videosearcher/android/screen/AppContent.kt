package com.amegane3231.videosearcher.android.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import com.amegane3231.videosearcher.android.theme.MovieSearcherTheme
import com.google.accompanist.insets.ProvideWindowInsets

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideoSearcherContent(currentScreen: Screen) {
    when (currentScreen) {
        Screen.Home -> HomeScreen()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideoSearcher() {
    val currentScreen by remember { mutableStateOf(Screen.Home) }
    MovieSearcherTheme {
        ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
            VideoSearcherContent(currentScreen = currentScreen)
        }
    }
}
