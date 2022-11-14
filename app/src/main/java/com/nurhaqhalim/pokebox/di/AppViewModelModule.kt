package com.nurhaqhalim.pokebox.di

import com.nurhaqhalim.pokebox.view.detail.DetailViewModel
import com.nurhaqhalim.pokebox.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}