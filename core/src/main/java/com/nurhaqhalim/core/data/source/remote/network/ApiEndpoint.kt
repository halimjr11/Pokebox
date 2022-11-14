package com.nurhaqhalim.core.data.source.remote.network

import com.nurhaqhalim.core.data.source.remote.response.PokemonDetail
import com.nurhaqhalim.core.data.source.remote.response.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndpoint {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getDetail(@Path("name") name: String) : PokemonDetail
}