package com.example.pokedex.ui.compose.utils

object StringUtils {

    fun convertPokemonUrl(pokemonUrl: String) = pokemonUrl.substringAfter("v2/")

}
