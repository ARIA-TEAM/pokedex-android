package com.example.pokedex.ui.main

import androidx.lifecycle.viewModelScope
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

    private val _pokemonFavoriteList: MutableStateFlow<List<PokemonListSummary>> by lazy {
        MutableStateFlow(mutableListOf())
    }

    val pokemonFavoriteList: StateFlow<List<PokemonListSummary>> = _pokemonFavoriteList

    private fun addPokemonToFavorite(pokemonName: String) {

        val currentFavoritePokemonList = pokemonFavoriteList.value
        _pokemonFavoriteList.value =
            currentFavoritePokemonList + PokemonListSummary(
                name = pokemonName,
                isFavorite = true
            )
    }

    /*
        fun removePokemonToFavorite(pokemonName: String) {
            val currentFavoritePokemon = pokemonFavoriteList.value ?: emptyList()
            pokemonFavoriteList.value?.forEachIndexed { index, pokemonListFavoriteSummary ->
                { pokemonName ->
                    if (pokemonName.name = pokemonName) {
                        pokemonFavoriteList.value[index].
                    }
                }

            }
        }
    */

    override val _viewData = MutableStateFlow<MainViewState>(MainViewState.Idle)

    override fun setStateEvent(stateEvent: StateEvent) {
        when (stateEvent) {
            is MainStateEvent.GetPokemon -> getPokemon(stateEvent.pokemonName)
            is MainStateEvent.GetPokemons -> getPokemons()
            is MainStateEvent.GetPokemonFavoriteList -> {}
            is MainStateEvent.AddPokemonToFavorite -> addPokemonToFavorite(stateEvent.pokemonName)
            // is MainStateEvent.GetPokemonByUrl -> getPokemonByUrl(stateEvent.pokemonUrl)
        }
    }

    /*
        private fun getPokemonByUrl(pokemonUrl: String) {
            _viewData.value = MainViewState.Loading
            viewModelScope.launch {
                _viewData.value = mainInteractor.getPokemon()
            }
        }
    */

    private fun getPokemon(pokemonName: String) {
        _viewData.value = MainViewState.Loading
        viewModelScope.launch {
            _viewData.value = mainInteractor.getPokemon(pokemonName)
        }
    }

    private fun getPokemons() {
        _viewData.value = MainViewState.Loading
        viewModelScope.launch {
            _viewData.value = mainInteractor.getPokemons()
        }
    }

}