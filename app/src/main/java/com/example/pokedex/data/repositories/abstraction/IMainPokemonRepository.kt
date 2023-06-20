package com.example.pokedex.data.repositories.abstraction

import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.data.model.PokemonSummary

interface IMainPokemonRepository {

    suspend fun getPokemon(pokemonName: String): PokemonSummary
    suspend fun getPokemons(): List<PokemonListSummary>

}