package com.example.pokedex.interactors.abstraction

import com.example.pokedex.data.model.PokemonDetails
import com.example.pokedex.ui.main.state.MainViewState

interface IMainInteractor {

    suspend fun getPokemon(pokemonNumberId: String): PokemonDetails
    suspend fun getPokemons(): MainViewState

}