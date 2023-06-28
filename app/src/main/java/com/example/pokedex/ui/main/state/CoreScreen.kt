package com.example.pokedex.ui.main.state

sealed class CoreScreen(val route: String) {
    object All : CoreScreen("all")
    object Favorites : CoreScreen("favorites")
}

sealed class PokedexMainScreens(val route: String) {
    object SplashScreen : PokedexMainScreens("splash_screen")
    object GetStartedScreen : PokedexMainScreens("get_started")
    object MainScreen : PokedexMainScreens("main_screen")
}