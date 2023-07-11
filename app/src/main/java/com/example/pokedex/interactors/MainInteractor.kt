package com.example.pokedex.interactors

import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.data.model.PokemonDetails
import com.example.pokedex.data.remote.HttpClient
import com.example.pokedex.data.remote.RequestResult
import com.example.pokedex.data.repositories.abstraction.IMainPokemonRepository
import com.example.pokedex.interactors.abstraction.IMainInteractor
import com.example.pokedex.ui.main.state.MainViewState
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class MainInteractor @Inject constructor(
    private val mainPokemonRepository: IMainPokemonRepository,
    private val httpClient: HttpClient,
    private val coroutineDispatcher: CoroutineDispatcher
) : IMainInteractor {

    override suspend fun getPokemon(pokemonNumberId: String): PokemonDetails {
        val result = httpClient.safeApiCall(coroutineDispatcher) {
            mainPokemonRepository.getPokemon(pokemonNumberId)
        }

        return when (result) {
            is RequestResult.Success -> {
                result.data!!
            }

            else -> PokemonDetails()
        }
    }

    override suspend fun getPokemons(): MainViewState {
        val result = httpClient.safeApiCall(coroutineDispatcher) {
            mainPokemonRepository.getPokemons()
        }

        return when (result) {
            is RequestResult.Success -> {
                MainViewState.GetPokemonListSuccess(
                    result.data ?: mutableListOf(PokemonListSummary(""))
                )
            }

            is RequestResult.RequestError -> {
                MainViewState.GetPokemonError(result.errorResponse)
            }

            else -> MainViewState.Idle
        }
    }

}
