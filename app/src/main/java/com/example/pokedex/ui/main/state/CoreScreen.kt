package com.example.pokedex.ui.main.state

sealed class CoreScreen(val route: String) {
    object All : CoreScreen("all")
    object Favorites : CoreScreen("favorites")
}

sealed class PokedexMainScreen(val route: String) {
    object SplashScreen : PokedexMainScreen("splash_screen")
    object GetStartedScreen : PokedexMainScreen("get_started")
    object MainScreen : PokedexMainScreen("main_screen")
}