package com.example.pokedex.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
internal annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
internal annotation class IoDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
internal annotation class MainDispatcher