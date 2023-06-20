package com.example.pokedex.ui.main.state

import com.example.pokedex.ui.base.StateEvent

abstract class MainStateEvent : StateEvent {

    data class GetPokemon(val pokemonName: String) : MainStateEvent()
    object GetPokemons : MainStateEvent()

}