package com.example.pokedex.interactors.abstraction

import com.example.pokedex.ui.main.state.MainViewState

interface IMainInteractor {

    suspend fun getPokemon(pokemonName: String): MainViewState
   // suspend fun getPokemonByUrl(pokemonUrl: String): MainViewState

    //suspend fun getFavoritePokemonList() : MainViewState
    suspend fun getPokemons(): MainViewState

}