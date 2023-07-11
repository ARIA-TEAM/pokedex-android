package com.example.pokedex.ui.main.state

import com.example.pokedex.data.model.PokemonDetails
import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.data.remote.model.ErrorResponse
import com.example.pokedex.ui.base.ViewState

sealed class MainViewState : ViewState {

    object Idle : MainViewState()
    object Loading : MainViewState()

    data class GetPokemonSuccess(
        val pokemonDetail: PokemonDetails
    ) : MainViewState()

    data class GetPokemonListSuccess(
        val pokemonListSummary: List<PokemonListSummary>
    ) : MainViewState()

    data class GetPokemonError(
        val error: ErrorResponse
    ) : MainViewState()

}
