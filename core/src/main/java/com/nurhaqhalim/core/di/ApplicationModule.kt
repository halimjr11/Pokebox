package com.nurhaqhalim.core.di

import com.nurhaqhalim.core.data.Repository
import com.nurhaqhalim.core.data.source.local.LocalSource
import com.nurhaqhalim.core.data.source.remote.RemoteSource
import com.nurhaqhalim.core.domain.repository.PokemonRepository
import com.nurhaqhalim.core.domain.usecase.PokemonInteractor
import com.nurhaqhalim.core.domain.usecase.PokemonUseCase
import org.koin.dsl.module

val applicationModule = module {
    single {
        RemoteSource(get())
    }

    single {
        LocalSource(get())
    }

    single<PokemonRepository> {
        Repository(get(), get())
    }

    single<PokemonUseCase> {
        PokemonInteractor(get())
    }
}