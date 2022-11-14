package com.nurhaqhalim.core.domain.usecase

import com.nurhaqhalim.core.domain.model.Pokemon
import com.nurhaqhalim.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class PokemonInteractor constructor(private val repo: PokemonRepository) : PokemonUseCase {
    override fun getAllPokemon(): Flow<List<Pokemon>> = repo.getAllPokemon()

    override fun getSavedPokemon(): Flow<List<Pokemon>> = repo.getSavedPokemon()

    override fun getDetailPokemon(name: String): Flow<Pokemon> = repo.getDetailPokemon(name)

    override fun saveAndSetNickname(id: Int, nickname: String) = repo.saveAndSetNickname(id, nickname)

    override fun unSavePokemon(id: Int) = repo.unSavePokemon(id)

    override fun checkSaved(id: Int): Int = repo.checkSaved(id)
}