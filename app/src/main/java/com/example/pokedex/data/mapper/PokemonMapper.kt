package com.example.pokedex.data.mapper

import com.example.pokedex.data.model.PokemonListSummary
import com.example.pokedex.data.model.PokemonDetails
import com.example.pokedex.data.remote.model.PokemonDetailResponse
import com.example.pokedex.data.remote.model.PokemonListResponse
import com.example.pokedex.data.remote.model.Types
import javax.inject.Inject

class PokemonMapper @Inject constructor() {

    fun remotePokemonToPokemonDetails(pokemonResponse: PokemonDetailResponse): PokemonDetails {
        return with(pokemonResponse) {
            PokemonDetails(
                pokemonName = name,
                height = height,
                weight = weight,
                types = mapPokemonTypesName(pokemonResponse.types),
                pokemonImg = sprites.other.dreamWorld.frontDefault
            )
        }
    }

    private fun mapPokemonTypesName(types: List<Types>): String {
        return types.joinToString(", ") { it.type.name.replaceFirstChar { char -> char.uppercaseChar() } }
    }

    fun remotePokemonListResponseToPokemonListSummary(pokemonListResponse: PokemonListResponse): List<PokemonListSummary> {
        return with(pokemonListResponse) {
            this.results.map {
                PokemonListSummary(name = it.name, url = it.url)
            }
        }
    }

}
