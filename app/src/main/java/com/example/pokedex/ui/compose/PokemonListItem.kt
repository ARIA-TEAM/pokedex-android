package com.example.pokedex.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.pokedex.R
import com.example.pokedex.data.model.PokemonDetails
import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.ui.main.state.MainStateEvent
import com.example.pokedex.ui.main.state.MainViewState

@Composable
fun PokemonListItem(
    pokemonDetails: PokemonDetails?,
    mainViewState: MainViewState,
    pokemonNumberId: String,
    pokemon: PokemonListSummary,
    onPokemonItemSelected: (MainStateEvent) -> Unit,
    onFavoriteIconPressed: (MainStateEvent) -> Unit,
    pokemonFavoriteList: List<PokemonListSummary>
) {
    val showDialog = remember { mutableStateOf(false) }

    ConstraintLayout(modifier = Modifier
        .clickable {
            showDialog.value = true
            onPokemonItemSelected(
                MainStateEvent.GetPokemonByUrl(
                    pokemonNumberId = pokemonNumberId
                )
            )
        }
        .padding(horizontal = 30.dp, vertical = 2.dp)
        .background(Color.White)
        .fillMaxWidth()) {

        val (name, favoriteIcon) = createRefs()

        Text(
            text = pokemon.name.replaceFirstChar { char -> char.uppercaseChar() },
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top, margin = 17.dp)
                start.linkTo(parent.start, margin = 20.dp)
                bottom.linkTo(parent.bottom, margin = 17.dp)
            }, style = MaterialTheme.typography.titleMedium
        )

        IconButton(onClick = {
            onFavoriteIconPressed(
                MainStateEvent.OnToggleFavoritePokemonButton(
                    PokemonListSummary(name = pokemon.name, url = pokemon.url)
                )
            )
        }, enabled = true, modifier = Modifier.constrainAs(favoriteIcon) {
            top.linkTo(parent.top, margin = 17.dp)
            end.linkTo(parent.end, margin = 20.dp)
            bottom.linkTo(parent.bottom, margin = 17.dp)
        }) {

            Image(
                if (pokemonFavoriteList.contains(pokemon)) painterResource(id = R.drawable.star_enabled) else painterResource(
                    id = R.drawable.star_disabled
                ),
                contentDescription = null,
            )
        }
        if (showDialog.value) {
            if (pokemonDetails != null) {
                CustomPokemonDetailDialog(
                    onDismiss = { showDialog.value = !showDialog.value },
                    onExit = { },
                    pokemonDetails = pokemonDetails,
                    pokemon = pokemon,
                    pokemonFavoriteList = pokemonFavoriteList,
                    onFavoriteIconPressed = onFavoriteIconPressed
                ) {
                    PokemonImgFromUrl(url = pokemonDetails.pokemonImg)
                }
            }
        }
    }
}

@Composable
fun PokemonFavoriteListItem(
    pokemon: PokemonListSummary,
    onPokemonItemSelected: (MainStateEvent) -> Unit,
    onFavoriteIconPressed: (MainStateEvent) -> Unit
) {
    ConstraintLayout(modifier = Modifier
        .clickable {
            onPokemonItemSelected(
                MainStateEvent.GetPokemonByUrl(
                    pokemon.url
                )
            )
        }
        .padding(horizontal = 30.dp, vertical = 2.dp)
        .background(Color.White)
        .fillMaxWidth()) {

        val (name, favoriteIcon) = createRefs()

        Text(text = pokemon.name.replaceFirstChar { char -> char.uppercaseChar() },
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top, margin = 17.dp)
                start.linkTo(parent.start, margin = 20.dp)
                bottom.linkTo(parent.bottom, margin = 17.dp)
            })

        IconButton(onClick = {
            onFavoriteIconPressed(
                MainStateEvent.RemovePokemonFromFavorites(pokemon)
            )
        }, enabled = true, modifier = Modifier.constrainAs(favoriteIcon) {
            top.linkTo(parent.top, margin = 17.dp)
            end.linkTo(parent.end, margin = 20.dp)
            bottom.linkTo(parent.bottom, margin = 17.dp)
        }) {

            Image(
                painterResource(id = R.drawable.star_enabled),
                contentDescription = null,
            )
        }
    }
}
