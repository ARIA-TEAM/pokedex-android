package com.example.pokedex.data.model

data class PokemonSummary(
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val types: List<Types> = mutableListOf()
)


data class Types(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)