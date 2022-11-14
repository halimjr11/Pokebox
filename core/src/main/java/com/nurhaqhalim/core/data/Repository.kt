package com.nurhaqhalim.core.data

import com.nurhaqhalim.core.data.source.local.LocalSource
import com.nurhaqhalim.core.data.source.local.entity.PokemonEntity
import com.nurhaqhalim.core.data.source.remote.RemoteSource
import com.nurhaqhalim.core.data.source.remote.network.ApiResponse
import com.nurhaqhalim.core.domain.model.Pokemon
import com.nurhaqhalim.core.domain.repository.PokemonRepository
import com.nurhaqhalim.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class Repository constructor(private val remote: RemoteSource, private val local: LocalSource) :
    PokemonRepository {
    override fun getAllPokemon(): Flow<List<Pokemon>> {
        val data = runBlocking { local.getAllPokemon().first() }
        return if (data.isEmpty()){
            flow{
                when (val apiResponse = run { remote.loadAllPokemon().first() }) {
                    is ApiResponse.Success -> {
                        apiResponse.data.results.forEach {
                            when(val detailResponse = run {remote.loadPokemonDetail(it.name).first()}) {
                                is ApiResponse.Success -> {
                                    val dt = PokemonEntity(
                                        ability = detailResponse.data.abilities[0].ability.name,
                                        name = detailResponse.data.name,
                                        nickname = "",
                                        height = detailResponse.data.height,
                                        weight = detailResponse.data.weight,
                                        image = detailResponse.data.sprites.front_default,
                                        type = detailResponse.data.types?.get(0)?.type?.name.toString(),
                                        apiId = detailResponse.data.id,
                                        hp = detailResponse.data.stat[0].baseStat!!,
                                        attack = detailResponse.data.stat[1].baseStat!!,
                                        defense = detailResponse.data.stat[2].baseStat!!,
                                        specialAttack = detailResponse.data.stat[3].baseStat!!,
                                        specialDefense = detailResponse.data.stat[4].baseStat!!,
                                        speed = detailResponse.data.stat[5].baseStat!!,
                                        isMine = false,
                                    )
                                    runBlocking { local.addToDb(dt) }
                                    emit(DataMapper.listEntityToListDomain(runBlocking {local.getAllPokemon().first()}))
                                }
                                is ApiResponse.Error -> emit(DataMapper.listEntityToListDomain(data))
                                else -> emit(DataMapper.listEntityToListDomain(data))
                            }
                        }
                    }
                    is ApiResponse.Error -> emit(DataMapper.listEntityToListDomain(data))
                    else -> emit(DataMapper.listEntityToListDomain(data))
                }
            }.flowOn(Dispatchers.IO)
        } else {
            runBlocking { local.getAllPokemon().map { DataMapper.listEntityToListDomain(it) } }
        }
    }

    override fun getSavedPokemon(): Flow<List<Pokemon>> {
        return runBlocking { local.getSavedList().map { DataMapper.listEntityToListDomain(it) } }
    }

    override fun getDetailPokemon(name: String): Flow<Pokemon> {
        return flow {
            val pokemon = kotlin.run { local.getDetailPokemon(name).first() }
            emit(DataMapper.entityToDomain(pokemon))
        }
    }

    override fun saveAndSetNickname(id: Int, nickname: String) = runBlocking { local.saveAndSetNickname(id, nickname) }

    override fun unSavePokemon(id: Int) = runBlocking { local.unSave(id) }

    override fun checkSaved(id: Int): Int = local.checkSaved(id)
}