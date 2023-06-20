package com.example.pokedex.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.pokedex.R
import com.example.pokedex.ui.theme.Gray

@Composable
fun PokedexLoaderScreen() {
    Column(
        modifier = Modifier
            .background(Gray)
            .fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.loader), contentDescription = null)
    }
}