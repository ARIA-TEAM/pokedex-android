package com.example.pokedex.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.pokedex.R
import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.ui.main.state.MainStateEvent

@Composable
fun PokemonListItem(
    pokemon: PokemonListSummary,
    onPokemonItemSelected: (MainStateEvent) -> Unit,
    onFavoriteIconPressed: (MainStateEvent) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .clickable {
                onPokemonItemSelected(
                    MainStateEvent.GetPokemonByUrl(
                        pokemon.url
                    )
                )
            }
            .padding(horizontal = 30.dp, vertical = 2.dp)
            .background(Color.White)
            .fillMaxWidth()
    ) {

        val (name, favoriteIcon) = createRefs()

        Text(text = pokemon.name.replaceFirstChar { char -> char.uppercaseChar() }, modifier = Modifier
            .constrainAs(name) {
                top.linkTo(parent.top, margin = 17.dp)
                start.linkTo(parent.start, margin = 20.dp)
                bottom.linkTo(parent.bottom, margin = 17.dp)
            })

        IconButton(onClick = { onFavoriteIconPressed(MainStateEvent.AddPokemonToFavorite(pokemon.name)) },
            enabled = true,
            modifier = Modifier.constrainAs(favoriteIcon) {
                top.linkTo(parent.top, margin = 17.dp)
                end.linkTo(parent.end, margin = 20.dp)
                bottom.linkTo(parent.bottom, margin = 17.dp)
            }) {

            Image(
                if (pokemon.isFavorite) painterResource(id = R.drawable.star_enabled) else painterResource(
                    id = R.drawable.star_disabled
                ),
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListItemPreview() {
    PokemonListItem(PokemonListSummary(), {}, {})
}