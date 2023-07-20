package com.example.pokedex.data.repositories.abstraction

import com.example.pokedex.data.model.PokemonDetails
import com.example.pokedex.data.model.PokemonListSummary

interface IMainPokemonRepository {

    suspend fun getPokemon(pokemonUrl: String): PokemonDetails
    suspend fun getPokemons(): List<PokemonListSummary>

}