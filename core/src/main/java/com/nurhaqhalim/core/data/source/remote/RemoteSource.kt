package com.nurhaqhalim.core.data.source.remote

import com.nurhaqhalim.core.data.source.remote.network.ApiEndpoint
import com.nurhaqhalim.core.data.source.remote.network.ApiResponse
import com.nurhaqhalim.core.data.source.remote.response.PokemonDetail
import com.nurhaqhalim.core.data.source.remote.response.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteSource constructor(private val api: ApiEndpoint) {
    suspend fun loadAllPokemon() : Flow<ApiResponse<PokemonResponse>> {
        return flow {
            try {
                val response = api.getPokemonList()
                if (response != null){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loadPokemonDetail(name:String) : Flow<ApiResponse<PokemonDetail>>{
        return flow {
            try {
                val response = api.getDetail(name)
                if (response != null){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }
    }
}