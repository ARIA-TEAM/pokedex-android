package com.example.pokedex.data.model

data class PokemonListSummary(
    val name: String,
    val url: String = "",
    var isFavorite: Boolean = false
)
data class PokemonDetails(
    val pokemonName: String = "",
    val pokemonImg: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val types: String = ""
)