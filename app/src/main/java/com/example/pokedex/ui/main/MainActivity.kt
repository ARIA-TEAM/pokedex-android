package com.example.pokedex.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.ui.base.BaseActivity
import com.example.pokedex.ui.compose.PokedexAppNavHost
import com.example.pokedex.ui.main.state.MainViewState
import com.example.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewState>() {

    private lateinit var navController: NavHostController
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val viewState by viewModel.viewData.collectAsState()
                    val favoritePokemonList by viewModel.pokemonFavoriteList.collectAsState()
                    PokedexAppNavHost(
                        mainViewState = viewState,
                        favoritePokemonList = favoritePokemonList,
                        navController = navController
                    )
                }
            }
        }
    }


    override fun render(viewState: MainViewState) {
        when (viewState) {
            is MainViewState.Loading -> {}
            is MainViewState.GetPokemonSuccess -> {}
            is MainViewState.GetPokemonListSuccess -> {
                viewState.pokemonListSummary
            }

            is MainViewState.GetPokemonError -> {}
            else -> Unit
        }
    }

}
