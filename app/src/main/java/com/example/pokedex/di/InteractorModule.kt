package com.example.pokedex.di

import com.example.pokedex.data.remote.HttpClient
import com.example.pokedex.data.repositories.abstraction.IMainPokemonRepository
import com.example.pokedex.interactors.MainInteractor
import com.example.pokedex.interactors.abstraction.IMainInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object InteractorModule {

    @Provides
    fun providesMainInteractor(
        mainPokemonRepository: IMainPokemonRepository,
        httpClient: HttpClient,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): IMainInteractor {
        return MainInteractor(mainPokemonRepository, httpClient, ioDispatcher)
    }

}