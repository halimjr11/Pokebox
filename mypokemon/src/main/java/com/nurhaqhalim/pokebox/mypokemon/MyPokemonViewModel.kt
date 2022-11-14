package com.nurhaqhalim.pokebox.mypokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nurhaqhalim.core.domain.model.Pokemon
import com.nurhaqhalim.core.domain.usecase.PokemonUseCase

class MyPokemonViewModel constructor(private val useCase: PokemonUseCase) : ViewModel() {
    fun getSavedList(): LiveData<List<Pokemon>> = useCase.getSavedPokemon().asLiveData()
}