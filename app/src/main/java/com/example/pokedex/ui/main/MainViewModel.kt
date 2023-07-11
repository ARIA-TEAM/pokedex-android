package com.example.pokedex.ui.main

import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.model.PokemonDetails
import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.interactors.abstraction.IMainInteractor
import com.example.pokedex.ui.base.BaseViewModel
import com.example.pokedex.ui.base.StateEvent
import com.example.pokedex.ui.main.state.MainStateEvent
import com.example.pokedex.ui.main.state.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainInteractor: IMainInteractor
) : BaseViewModel<MainViewState>() {

    private val _pokemonDetails: MutableStateFlow<PokemonDetails?> by lazy {
        MutableStateFlow(null)
    }
    val pokemonDetails: StateFlow<PokemonDetails?> = _pokemonDetails

    private val _pokemonFavoriteList: MutableStateFlow<List<PokemonListSummary>> by lazy {
        MutableStateFlow(mutableListOf())
    }

    val pokemonFavoriteList: StateFlow<List<PokemonListSummary>> = _pokemonFavoriteList

    private fun onToggleFavoritePokemonButton(pokemon: PokemonListSummary) {
        val currentFavorites = _pokemonFavoriteList.value.toMutableList()
         if (!currentFavorites.contains(pokemon)) {
            val updatedPokemonFavoriteList = _pokemonFavoriteList.value.toMutableList().apply {
                add(pokemon)
            }
            _pokemonFavoriteList.value = updatedPokemonFavoriteList
        } else {
            _pokemonFavoriteList.value = _pokemonFavoriteList.value.toMutableList().apply {
                remove(pokemon)
            }
        }
    }

    private fun removePokemonFromFavorites(pokemon: PokemonListSummary) {
        _pokemonFavoriteList.value = _pokemonFavoriteList.value.toMutableList().apply {
            remove(pokemon)
        }
    }

    override val _viewData = MutableStateFlow<MainViewState>(MainViewState.Idle)

    override fun setStateEvent(stateEvent: StateEvent) {
        when (stateEvent) {
            is MainStateEvent.GetPokemonByUrl -> getPokemon(stateEvent.pokemonNumberId)
            is MainStateEvent.GetPokemon -> getPokemon(stateEvent.pokemonName)
            is MainStateEvent.GetPokemons -> getPokemons()
            is MainStateEvent.GetPokemonFavoriteList -> {}
            is MainStateEvent.OnToggleFavoritePokemonButton -> onToggleFavoritePokemonButton(
                stateEvent.pokemon
            )

            is MainStateEvent.RemovePokemonFromFavorites -> removePokemonFromFavorites(stateEvent.pokemon)
        }
    }

    private fun getPokemon(pokemonNumberId: String) {
        viewModelScope.launch {
            _pokemonDetails.value = mainInteractor.getPokemon(pokemonNumberId)
        }
    }

    private fun getPokemons() {
        _viewData.value = MainViewState.Loading
        viewModelScope.launch {
            _viewData.value = mainInteractor.getPokemons()
        }
    }

}