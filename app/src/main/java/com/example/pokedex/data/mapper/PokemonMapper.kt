package com.example.pokedex.data.mapper

import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.data.model.PokemonSummary
import com.example.pokedex.data.model.Type
import com.example.pokedex.data.remote.model.PokemonDetailResponse
import com.example.pokedex.data.remote.model.PokemonListResponse
import com.example.pokedex.data.remote.model.Types

import javax.inject.Inject

class PokemonMapper @Inject constructor() {

    fun remotePokemonToPokemonSummary(pokemonResponse: PokemonDetailResponse): PokemonSummary {
        return with(pokemonResponse) {
            PokemonSummary(
                name = name,
                height = height,
                weight = weight,
                types = mapPokemonTypes(pokemonResponse.types)
            )
        }
    }

    private fun mapPokemonTypes(types: List<Types>): List<com.example.pokedex.data.model.Types> {
        return types.map {
            com.example.pokedex.data.model.Types(
                slot = it.slot, type = getPokemonType(it.type)
            )
        }
    }

    private fun getPokemonType(type: com.example.pokedex.data.remote.model.Type): Type {
        return Type(
            name = type.name, url = type.url
        )
    }


    fun remotePokemonListResponseToPokemonListSummary(pokemonListResponse: PokemonListResponse): List<PokemonListSummary> {
        return with(pokemonListResponse) {
            this.results.map {
                PokemonListSummary(name = it.name)
            }
        }
    }

}

