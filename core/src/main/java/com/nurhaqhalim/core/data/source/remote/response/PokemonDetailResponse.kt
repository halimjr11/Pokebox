package com.nurhaqhalim.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue

data class PokemonDetail(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("height")
    val height: Int,

    @SerializedName("weight")
    val weight: Int,

    @SerializedName("abilities")
    val abilities: List<PokemonAbility>,

    @SerializedName("stats")
    val stat:List<PokemonStat>,

    @SerializedName("types")
    val types: List<PokemonType>?,

    @SerializedName("sprites")
    val sprites: Sprites
)

data class PokemonAbility(
    @SerializedName("ability")
    val ability: @RawValue NameAndUrl,

    @SerializedName("is_hidden")
    val isHidden: Boolean,

    @SerializedName("slot")
    val slot: Int
)

data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int?,

    @SerializedName("effort")
    val effort: Int?,

    @SerializedName("stat")
    val stat: @RawValue NameAndUrl
)

data class PokemonType(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: @RawValue NameAndUrl
)

data class Sprites(
    val front_default: String,

    @SerializedName("other")
    val otherSprites: @RawValue OtherSprites
)

data class OtherSprites(
    @SerializedName("official-artwork")
    val artwork: @RawValue OfficialArtwork
)

data class OfficialArtwork(
    val front_default: String?
)

data class NameAndUrl(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)
