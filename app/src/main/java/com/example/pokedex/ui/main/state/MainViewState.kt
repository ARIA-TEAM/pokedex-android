package com.example.pokedex.ui.main.state

import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.data.model.PokemonSummary
import com.example.pokedex.data.remote.model.ErrorResponse
import com.example.pokedex.ui.base.ViewState

sealed class MainViewState : ViewState {

    object Idle : MainViewState()
    object Loading : MainViewState()

    data class GetPokemonSuccess(
        val pokemonSummary: PokemonSummary
    ) : MainViewState()

    data class GetPokemonListSuccess(
        val pokemonListSummary: List<PokemonListSummary>
    ) : MainViewState()

    data class GetPokemonError(
        val error: ErrorResponse
    ) : MainViewState()

}
