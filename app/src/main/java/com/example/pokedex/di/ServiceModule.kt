package com.example.pokedex.di

import com.example.pokedex.data.remote.services.PokemonAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {

    @Provides
    fun providesPokemonAPIService(retrofit: Retrofit): PokemonAPIService {
        return retrofit.create(PokemonAPIService::class.java)
    }

}