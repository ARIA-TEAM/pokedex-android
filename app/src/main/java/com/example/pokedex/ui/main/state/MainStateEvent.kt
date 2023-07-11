package com.example.pokedex.ui.main.state

import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.ui.base.StateEvent

sealed class MainStateEvent : StateEvent {

    data class GetPokemon(val pokemonName: String) : MainStateEvent()
    data class GetPokemonByUrl(val pokemonNumberId: String) : MainStateEvent()
    data class OnToggleFavoritePokemonButton(val pokemon: PokemonListSummary) : MainStateEvent()
    data class RemovePokemonFromFavorites(val pokemon: PokemonListSummary) : MainStateEvent()
    object GetPokemonFavoriteList : MainStateEvent()
    object GetPokemons : MainStateEvent()

}
