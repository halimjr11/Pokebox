package com.nurhaqhalim.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import com.nurhaqhalim.core.data.source.remote.response.NameAndUrl
import com.nurhaqhalim.core.data.source.remote.response.PokemonAbility
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Pokemon(
    var id : Int = 0,
    var ability: String = "",
    var name: String = "",
    var nickname: String = "",
    val height: Int = 0,
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val specialAttack: Int = 0,
    val specialDefense: Int = 0,
    val speed: Int = 0,
    var apiId: Int = 0,
    var image: String = "",
    var type: String = "",
    val weight: Int = 0,
    var isMine: Boolean = false
) : Parcelable

