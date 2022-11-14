package com.nurhaqhalim.core.utils

import com.nurhaqhalim.core.data.source.local.entity.PokemonEntity
import com.nurhaqhalim.core.domain.model.Pokemon

object DataMapper {
    fun listEntityToListDomain(list: List<PokemonEntity>) : List<Pokemon> =
        list.map {
            Pokemon(
                it.id ?: 0,
                it.ability,
                it.name,
                it.nickname,
                it.height,
                it.hp,
                it.attack,
                it.defense,
                it.specialAttack,
                it.specialDefense,
                it.speed,
                it.apiId,
                it.image ?: "",
                it.type,
                it.weight,
                it.isMine
            )
        }

    fun entityToDomain(pokemonEntity: PokemonEntity) : Pokemon = Pokemon(
        pokemonEntity.id ?: 0,
        pokemonEntity.ability,
        pokemonEntity.name,
        pokemonEntity.nickname,
        pokemonEntity.height,
        pokemonEntity.hp,
        pokemonEntity.attack,
        pokemonEntity.defense,
        pokemonEntity.specialAttack,
        pokemonEntity.specialDefense,
        pokemonEntity.speed,
        pokemonEntity.apiId,
        pokemonEntity.image ?: "",
        pokemonEntity.type,
        pokemonEntity.weight,
        pokemonEntity.isMine
    )
}