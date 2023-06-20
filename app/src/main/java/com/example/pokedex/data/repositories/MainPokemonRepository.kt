package com.example.pokedex.data.repositories

import com.example.pokedex.data.mapper.PokemonMapper
import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.data.model.PokemonSummary
import com.example.pokedex.data.remote.datasources.PokemonDataSource
import com.example.pokedex.data.repositories.abstraction.IMainPokemonRepository
import javax.inject.Inject

class MainPokemonRepository @Inject constructor(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonMapper: PokemonMapper
) : IMainPokemonRepository {

    override suspend fun getPokemon(pokemonName: String): PokemonSummary {
        return pokemonMapper.remotePokemonToPokemonSummary(
            pokemonDataSource.getPokemon(pokemonName)
        )
    }

    override suspend fun getPokemons(): List<PokemonListSummary> {
        return pokemonMapper.remotePokemonListResponseToPokemonListSummary(
            pokemonDataSource.getPokemons()
        )
    }

}