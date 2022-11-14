package com.nurhaqhalim.core.domain.repository

import com.nurhaqhalim.core.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getAllPokemon() : Flow<List<Pokemon>>
    fun getSavedPokemon() : Flow<List<Pokemon>>
    fun getDetailPokemon(name:String): Flow<Pokemon>
    fun saveAndSetNickname(id: Int, nickname:String)
    fun unSavePokemon(id: Int)
    fun checkSaved(id:Int) : Int
}