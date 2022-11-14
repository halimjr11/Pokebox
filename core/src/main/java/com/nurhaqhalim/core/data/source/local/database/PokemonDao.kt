package com.nurhaqhalim.core.data.source.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nurhaqhalim.core.data.source.local.entity.PokemonEntity
import com.nurhaqhalim.core.data.source.remote.response.PokemonDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPokemon(pokemon: PokemonEntity)

    @Query("UPDATE pokemon SET is_mine = 1, nickname = :nick WHERE id = :id")
    fun setNicknameAndSave(id: Int, nick: String)

    @Query("UPDATE pokemon SET is_mine = 0 WHERE id = :id")
    fun unSavePokemon(id: Int)

    @Query("SELECT count(*) FROM pokemon WHERE is_mine = 1 AND id = :id")
    suspend fun checkSaved(id: Int): Int

    @Query("SELECT * FROM pokemon")
    fun getAllList(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon WHERE is_mine = 1 ORDER BY name ASC")
    fun getSavedData() : Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon WHERE name = :name")
    fun getDetail(name: String) : Flow<PokemonEntity>
}