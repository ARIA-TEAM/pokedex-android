package com.example.pokedex.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.pokedex.R
import com.example.pokedex.data.model.PokemonDetails
import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.ui.main.state.MainStateEvent
import com.example.pokedex.ui.theme.GrayDialogSeparator

@Composable
fun CustomPokemonDetailDialog(
    pokemon: PokemonListSummary,
    onFavoriteIconPressed: (MainStateEvent) -> Unit,
    onDismiss: () -> Unit,
    onExit: () -> Unit,
    pokemonDetails: PokemonDetails,
    pokemonFavoriteList: List<PokemonListSummary>,
    content: @Composable () -> Unit,
) {

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .height(510.dp)
                .width(315.dp)
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(240.dp)
                        .width(315.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pokemon_font_detail),
                        contentDescription = "Pokemon Detail Font",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier
                            .height(180.dp)
                            .width(180.dp)
                    ) {
                        content()
                    }
                    Box(
                        contentAlignment = Alignment.TopEnd,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 2.dp, top = 2.dp)
                    ) {
                        IconButton(onClick = { onDismiss() }) {
                            Image(
                                painter = painterResource(id = R.drawable.close_detail_dialog),
                                contentDescription = "Close"
                            )
                        }
                    }
                }
                //info pokemon here
                DialogPokemonInfoDetail(pokemonDetails = pokemonDetails)
                DialogButtonsComponent(
                    pokemonFavoriteList = pokemonFavoriteList,
                    pokemon = pokemon,
                    onFavoriteIconPressed = onFavoriteIconPressed
                )
            }
        }
    }
}

@Composable
fun DialogButtonsComponent(
    pokemonFavoriteList: List<PokemonListSummary>,
    pokemon: PokemonListSummary,
    onFavoriteIconPressed: (MainStateEvent) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 10.dp)
    ) {
        Button(modifier = Modifier,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            onClick = {}) {
            Text(text = stringResource(id = R.string.shared_button_title))
        }
        IconButton(onClick = {
            onFavoriteIconPressed(
                MainStateEvent.OnToggleFavoritePokemonButton(
                    PokemonListSummary(name = pokemon.name, url = pokemon.url)
                )
            )
        }, enabled = true) {

            Image(
                if (pokemonFavoriteList.contains(pokemon)) painterResource(id = R.drawable.star_enabled)
                else painterResource(id = R.drawable.star_disabled), contentDescription = null
            )
        }
    }
}

@Composable
fun PokemonImgFromUrl(url: String?) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(url)
            .decoderFactory(SvgDecoder.Factory()).build(), contentDescription = "pokemon"
    )
}


@Composable
fun ShowDialog(imageUrl: String) {
    Dialog(
        onDismissRequest = { /* Cerrar diálogo */ },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            PokemonImgFromUrl(url = imageUrl)
            IconButton(
                onClick = { /* Cerrar diálogo */ }, modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar",
                    tint = Color.Gray
                )
            }
        }
    }
}


@Composable
fun DialogPokemonInfoDetail(pokemonDetails: PokemonDetails) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.pokemon_info_dialog_name),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pokemonDetails.pokemonName.replaceFirstChar { char -> char.uppercaseChar() },
                Modifier.padding(start = 4.dp),
                fontSize = 18.sp
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            color = GrayDialogSeparator,
            thickness = 1.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.pokemon_info_dialog_weight),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pokemonDetails.weight.toString(),
                Modifier.padding(start = 4.dp),
                fontSize = 18.sp
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            color = GrayDialogSeparator,
            thickness = 1.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.pokemon_info_dialog_height),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pokemonDetails.height.toString(),
                Modifier.padding(start = 4.dp),
                fontSize = 18.sp
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            color = GrayDialogSeparator,
            thickness = 1.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.pokemon_info_dialog_types),
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = pokemonDetails.types, Modifier.padding(start = 4.dp), fontSize = 18.sp)
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            color = GrayDialogSeparator,
            thickness = 1.dp
        )
    }
}
