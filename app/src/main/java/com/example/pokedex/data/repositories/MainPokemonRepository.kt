package com.example.pokedex.data.repositories

import com.example.pokedex.data.mapper.PokemonMapper
import com.example.pokedex.data.model.PokemonDetails
import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.data.remote.datasources.PokemonDataSource
import com.example.pokedex.data.repositories.abstraction.IMainPokemonRepository
import javax.inject.Inject

class MainPokemonRepository @Inject constructor(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonMapper: PokemonMapper
) : IMainPokemonRepository {

    //move mappers to UseCases(interactors) instead this.
    override suspend fun getPokemon(pokemonUrl: String): PokemonDetails {
        return pokemonMapper.remotePokemonToPokemonDetails(
            pokemonDataSource.getPokemonByUrl(pokemonUrl)
        )
    }

    override suspend fun getPokemons(): List<PokemonListSummary> {
        return pokemonMapper.remotePokemonListResponseToPokemonListSummary(
            pokemonDataSource.getPokemons()
        )
    }

}
