package com.example.pokedex.data.remote.datasources

import com.example.pokedex.data.remote.model.PokemonDetailResponse
import com.example.pokedex.data.remote.model.PokemonListResponse
import com.example.pokedex.data.remote.services.PokemonAPIService
import javax.inject.Inject

class PokemonDataSource @Inject constructor(
    private val pokemonApiService: PokemonAPIService
) {

    suspend fun getPokemonByUrl(pokemonNumberId: String): PokemonDetailResponse {
        return pokemonApiService.getPokemonByUrl(pokemonNumberId)
    }

    suspend fun getPokemons(): PokemonListResponse {
        return pokemonApiService.getPokemons()
    }

}