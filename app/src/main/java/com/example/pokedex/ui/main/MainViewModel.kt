package com.example.pokedex.ui.main

import androidx.lifecycle.viewModelScope
import com.example.pokedex.interactors.abstraction.IMainInteractor
import com.example.pokedex.ui.base.BaseViewModel
import com.example.pokedex.ui.base.StateEvent
import com.example.pokedex.ui.main.state.MainStateEvent
import com.example.pokedex.ui.main.state.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainInteractor: IMainInteractor
) : BaseViewModel<MainViewState>() {

    override val _viewData = MutableStateFlow<MainViewState>(MainViewState.Idle)

    override fun setStateEvent(stateEvent: StateEvent) {
        when (stateEvent) {
            is MainStateEvent.GetPokemon -> getPokemon(stateEvent.pokemonName)
            is MainStateEvent.GetPokemons -> getPokemons()
        }
    }

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