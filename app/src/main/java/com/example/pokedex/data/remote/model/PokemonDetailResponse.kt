package com.example.pokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("types") val types: List<Types>,
    @SerializedName("sprites") val sprites: Sprites
)

data class Sprites(
    @SerializedName("other") val other: Other
)

data class Other(
    @SerializedName("dream_world") val dreamWorld: DreamWorld,
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?
)

data class DreamWorld(
    @SerializedName("front_default") val frontDefault: String?
)

data class Types(
    @SerializedName("slot") val slot: Int, @SerializedName("type") val type: Type
)

data class Type(
    @SerializedName("name") val name: String, @SerializedName("url") val url: String
)

data class PokemonListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PokemonListDetail>
)

data class PokemonListDetail(
    @SerializedName("name") val name: String, @SerializedName("url") val url: String
)