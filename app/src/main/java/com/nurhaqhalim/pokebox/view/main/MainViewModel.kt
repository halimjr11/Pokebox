package com.nurhaqhalim.pokebox.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nurhaqhalim.core.domain.model.Pokemon
import com.nurhaqhalim.core.domain.usecase.PokemonUseCase

class MainViewModel constructor(private val useCase: PokemonUseCase) : ViewModel() {
    fun getAllData() : LiveData<List<Pokemon>> = useCase.getAllPokemon().asLiveData()
}