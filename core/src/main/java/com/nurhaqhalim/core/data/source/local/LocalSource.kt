package com.nurhaqhalim.core.data.source.local

import com.nurhaqhalim.core.data.source.local.database.PokemonDao
import com.nurhaqhalim.core.data.source.local.entity.PokemonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class LocalSource constructor(private val dao: PokemonDao) {
    suspend fun getAllPokemon() : Flow<List<PokemonEntity>> = withContext(Dispatchers.IO){
        dao.getAllList()
    }

    suspend fun getDetailPokemon(name : String) : Flow<PokemonEntity> = withContext(Dispatchers.IO){
        dao.getDetail(name)
    }

    suspend fun addToDb(pokemonEntity: PokemonEntity) = withContext(Dispatchers.IO){
        dao.addPokemon(pokemonEntity)
    }

    suspend fun getSavedList() : Flow<List<PokemonEntity>> = withContext(Dispatchers.IO){
        dao.getSavedData()
    }

    suspend fun saveAndSetNickname(id: Int, nickname:String) = withContext(Dispatchers.IO){
        dao.setNicknameAndSave(id, nickname)
    }

    suspend fun unSave(id: Int) = withContext(Dispatchers.IO){
        dao.unSavePokemon(id)
    }

    fun checkSaved(id:Int) : Int = runBlocking {
        val data = async {
            dao.checkSaved(id)
        }
        data.start()
        data.await()
    }

}