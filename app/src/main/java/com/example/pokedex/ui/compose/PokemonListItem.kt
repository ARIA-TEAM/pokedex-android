package com.example.pokedex.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

@Composable
fun PokemonListItem(pokemonName: String, onSelected: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 2.dp)
            .background(Color.White)
            .fillMaxWidth()
    ) {

        val (name, favoriteIcon) = createRefs()

        Text(text = pokemonName, modifier = Modifier.constrainAs(name) {
            top.linkTo(parent.top, margin = 17.dp)
            start.linkTo(parent.start, margin = 20.dp)
            bottom.linkTo(parent.bottom, margin = 17.dp)
        })

        IconButton(onClick = { onSelected() }, enabled = false,
            modifier = Modifier
                .constrainAs(favoriteIcon) {
                    top.linkTo(parent.top, margin = 17.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    bottom.linkTo(parent.bottom, margin = 17.dp)
                })
        {

            Image(

                /*   if (pokemonItemState.isFavorite) painterResource(id = R.drawable.star_enabled) else painterResource(
                       id = R.drawable.star_disabled
                   ),*/
                painterResource(id = R.drawable.star_enabled),
                contentDescription = null
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListItemPreview() {
    PokemonListItem("PokemonItemState()", {})
}