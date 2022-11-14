package com.nurhaqhalim.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nurhaqhalim.core.data.source.local.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun PokemonDao() : PokemonDao
}