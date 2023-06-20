package com.example.pokedex.di

import com.example.pokedex.data.mapper.PokemonMapper
import com.example.pokedex.data.remote.datasources.PokemonDataSource
import com.example.pokedex.data.repositories.MainPokemonRepository
import com.example.pokedex.data.repositories.abstraction.IMainPokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesMainPokemonRepository(
        pokemonDataSource: PokemonDataSource,
        pokemonMapper: PokemonMapper
    ): IMainPokemonRepository {
        return MainPokemonRepository(pokemonDataSource, pokemonMapper)
    }

}