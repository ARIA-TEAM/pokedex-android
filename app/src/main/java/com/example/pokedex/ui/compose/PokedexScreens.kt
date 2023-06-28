package com.example.pokedex.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokedex.R
import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.ui.main.MainViewModel
import com.example.pokedex.ui.main.state.MainStateEvent
import com.example.pokedex.ui.main.state.MainViewState
import com.example.pokedex.ui.main.state.PokedexMainScreens
import com.example.pokedex.ui.theme.Gray
import kotlinx.coroutines.delay

@Composable
fun PokedexAppNavHost(
    mainViewState: MainViewState,
    favoritePokemonList: List<PokemonListSummary>,
    navController: NavHostController,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    NavHost(
        navController = navController, startDestination = PokedexMainScreens.SplashScreen.route
    ) {
        composable(PokedexMainScreens.SplashScreen.route) { PokedexSplashScreen(navController) }
        composable(PokedexMainScreens.GetStartedScreen.route) {
            PokedexGetStartedScreen(
                mainViewState = mainViewState,
                navController = navController,
                viewModel::setStateEvent
            )
        }
        composable(PokedexMainScreens.MainScreen.route) {
            PokedexMainScreen(
                mainViewState = mainViewState,
                favoritePokemonList = favoritePokemonList,
                onPokemonItemSelected = viewModel::setStateEvent,
                onFavoriteIconPressed = viewModel::setStateEvent
            )
        }
    }
}

@Composable
fun PokedexSplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate(PokedexMainScreens.GetStartedScreen.route)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.loader), contentDescription = null)
        }
    }
}

@Composable
fun DetailScreen(
    pokemonFavoriteList: List<PokemonListSummary>,
    onPokemonItemSelected: (MainStateEvent) -> Unit,
    onFavoriteIconPressed: (MainStateEvent) -> Unit
) {
    ShowFavoritePokemonList(
        pokemonListSummary = pokemonFavoriteList,
        onPokemonItemSelected = onPokemonItemSelected,
        onFavoriteIconPressed = onFavoriteIconPressed
    )
}

@Composable
fun PokemonListScreen(
    mainViewState: MainViewState,
    navController: NavHostController,
    onPokemonItemSelected: (MainStateEvent) -> Unit,
    onFavoriteIconPressed: (MainStateEvent) -> Unit
) {
    when (mainViewState) {
        is MainViewState.Loading -> ShowLoader()
        is MainViewState.GetPokemonListSuccess -> ShowPokemonList(
            pokemonListSummary = mainViewState.pokemonListSummary,
            onPokemonItemSelected = onPokemonItemSelected,
            onFavoriteIconPressed = onFavoriteIconPressed
        )

        else -> Unit
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ShowFavoritePokemonList(
    pokemonListSummary: List<PokemonListSummary>,
    onPokemonItemSelected: (MainStateEvent) -> Unit,
    onFavoriteIconPressed: (MainStateEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (pokemonListSummary.isNotEmpty()) {
            ShowPokemonList(
                pokemonListSummary = pokemonListSummary,
                onPokemonItemSelected = onPokemonItemSelected,
                onFavoriteIconPressed = onFavoriteIconPressed
            )
        } else {
            var text by remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                },
                value = text,
                onValueChange = { textFieldValue ->
                    text = textFieldValue
                },
                placeholder = { Text(text = "Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 40.dp),
                singleLine = true,
                shape = TextFieldDefaults.filledShape,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Text(text = "No pokemons")
            Text(
                text = "The digital encyclopedia created by Professor Oak is an " +
                        "invaluable tool to Trainers in the Pokémon world."
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowPokemonList(
    pokemonListSummary: List<PokemonListSummary>,
    onPokemonItemSelected: (MainStateEvent) -> Unit,
    onFavoriteIconPressed: (MainStateEvent) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            },
            value = text,
            onValueChange = { textFieldValue ->
                text = textFieldValue
            },
            placeholder = { Text(text = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 40.dp),
            singleLine = true,
            shape = TextFieldDefaults.filledShape,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(pokemonListSummary.size) {
                PokemonListItem(
                    pokemonListSummary[it],
                    onPokemonItemSelected = onPokemonItemSelected,
                    onFavoriteIconPressed = onFavoriteIconPressed
                )
            }
        }
    }

}

@Composable
fun ShowLoader() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.loader), contentDescription = null)
        }

    }

}

@Composable
fun PokedexGetStartedScreen(
    mainViewState: MainViewState,
    navController: NavHostController,
    onGetEvent: (MainStateEvent) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray)
    ) {
        val localConfig = LocalConfiguration.current
        val screenHeight = localConfig.screenHeightDp
        val margin = 40.dp
        val (image, title, subtitle, button) = createRefs()

        Image(
            alignment = Alignment.Center, modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top, margin = margin)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(title.top, margin = 70.dp)
            }, painter = painterResource(id = R.drawable.pikachu), contentDescription = null
        )
        Text(
            textAlign = TextAlign.Center,
            maxLines = 3,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(image.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            },
            text = stringResource(id = R.string.get_started_screen_title),
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(subtitle) {
                top.linkTo(title.bottom, margin = margin)
                end.linkTo(parent.end, margin = 30.dp)
                start.linkTo(parent.start, margin = 30.dp)
            },
            text = stringResource(id = R.string.get_started_screen_sub_title),
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium
        )

        Button(modifier = Modifier.constrainAs(button) {
            top.linkTo(subtitle.bottom, margin = margin)
            end.linkTo(parent.end)
            start.linkTo(parent.start)
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red), onClick = {
            onGetEvent(MainStateEvent.GetPokemons)
            navController.navigate(PokedexMainScreens.MainScreen.route)
        }) {
            Text(text = "Get Started")
        }
    }

}
