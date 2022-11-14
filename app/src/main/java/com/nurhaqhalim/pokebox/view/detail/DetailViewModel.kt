package com.nurhaqhalim.pokebox.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nurhaqhalim.core.domain.model.Pokemon
import com.nurhaqhalim.core.domain.usecase.PokemonUseCase

class DetailViewModel constructor(private val useCase: PokemonUseCase) : ViewModel() {
    fun getDetail(username: String) : LiveData<Pokemon> = useCase.getDetailPokemon(username).asLiveData()
    fun saveAndSetNickname(id: Int, nickname: String) = useCase.saveAndSetNickname(id, nickname)
    fun unSave(id: Int) = useCase.unSavePokemon(id)
    fun checkSave(id: Int) = useCase.checkSaved(id)
}