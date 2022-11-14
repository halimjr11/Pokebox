package com.nurhaqhalim.core.data.source.local.entity

import androidx.room.*
import com.nurhaqhalim.core.utils.Constants

@Entity(tableName = Constants.tableName,  indices = (arrayOf(Index(value = arrayOf("name"), unique = true))))
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "ability")
    val ability: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "nickname")
    val nickname: String,

    @ColumnInfo(name = "height")
    val height: Int,

    @ColumnInfo(name = "hp")
    val hp: Int,

    @ColumnInfo(name = "attack")
    val attack: Int,

    @ColumnInfo(name = "defense")
    val defense: Int,

    @ColumnInfo(name = "special_attack")
    val specialAttack: Int,

    @ColumnInfo(name = "special_defense")
    val specialDefense: Int,

    @ColumnInfo(name = "speed")
    val speed: Int,

    @ColumnInfo(name = "image")
    val image: String? =null,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "api_id")
    val apiId: Int,

    @ColumnInfo(name = "weight")
    val weight: Int,

    @ColumnInfo(name = "is_mine")
    var isMine: Boolean = false
)
