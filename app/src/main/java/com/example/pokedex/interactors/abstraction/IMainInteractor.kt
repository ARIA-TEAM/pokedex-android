package com.example.pokedex.interactors.abstraction

import com.example.pokedex.data.model.PokemonDetails
import com.example.pokedex.data.model.PokemonListSummary

interface IMainInteractor {

    suspend fun getPokemon(pokemonUrl: String): PokemonDetails
    suspend fun getPokemons(): List<PokemonListSummary>

}