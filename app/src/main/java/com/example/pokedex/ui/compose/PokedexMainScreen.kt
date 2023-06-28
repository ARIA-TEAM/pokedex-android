package com.example.pokedex.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.ui.main.state.CoreScreen
import com.example.pokedex.ui.main.state.MainStateEvent
import com.example.pokedex.ui.main.state.MainViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexMainScreen(
    mainViewState: MainViewState,
    favoritePokemonList: List<PokemonListSummary>,
    onPokemonItemSelected: (MainStateEvent) -> Unit,
    onFavoriteIconPressed: (MainStateEvent) -> Unit,
) {
    val navController = rememberNavController()
    Scaffold(containerColor = Color.Gray, bottomBar = {
        BottomAppBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
            contentPadding = PaddingValues(
                start = 30.dp, top = 10.dp, bottom = 10.dp, end = 30.dp
            )
        ) {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry.value?.destination
            ConstraintLayout() {

                val (buttonAll, buttonFavorites) = createRefs()

                Button(
                    modifier = Modifier.constrainAs(buttonAll) {
                        start.linkTo(parent.start, margin = 30.dp)
                        end.linkTo(buttonFavorites.start, margin = 30.dp)
                        top.linkTo(parent.top, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                    onClick = {
                        navController.navigate(CoreScreen.All.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = ButtonDefaults.outlinedShape
                ) {
                    Icon(Icons.Filled.List, contentDescription = null)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "All")
                }
                Button(
                    modifier = Modifier.constrainAs(buttonFavorites) {
                        start.linkTo(buttonAll.end)
                        top.linkTo(parent.top, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                    },
                    onClick = {
                        navController.navigate(CoreScreen.Favorites.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = ButtonDefaults.outlinedShape
                ) {
                    Icon(Icons.Filled.Star, contentDescription = null)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Favorites")
                }
            }
        }
    }) { innerPadding ->
        NavHost(
            navController, startDestination = CoreScreen.All.route, Modifier.padding(innerPadding)
        ) {
            composable(CoreScreen.All.route) {
                PokemonListScreen(
                    mainViewState = mainViewState,
                    navController,
                    onPokemonItemSelected = onPokemonItemSelected,
                    onFavoriteIconPressed = onFavoriteIconPressed
                )
            }
            composable(CoreScreen.Favorites.route) {
                DetailScreen(
                    pokemonFavoriteList = favoritePokemonList,
                    onPokemonItemSelected = onPokemonItemSelected,
                    onFavoriteIconPressed = onFavoriteIconPressed
                )
            }
        }
    }

}
