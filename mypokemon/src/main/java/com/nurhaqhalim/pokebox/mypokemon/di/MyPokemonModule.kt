package com.nurhaqhalim.pokebox.mypokemon.di

import com.nurhaqhalim.pokebox.mypokemon.MyPokemonViewModel
import com.nurhaqhalim.pokebox.view.detail.DetailViewModel
import com.nurhaqhalim.pokebox.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myPokemonModule = module {
    viewModel { MyPokemonViewModel(get()) }
}