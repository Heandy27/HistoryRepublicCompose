package com.example.historyrepublic.data.network.api

import com.example.historyrepublic.data.network.model.HerosResponse
import com.example.historyrepublic.data.network.model.SingleHeroResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApi {
    @GET("api/heroes")
    suspend fun getHeros(): List<HerosResponse>

    @GET("api/heroes/{id}")
    suspend fun fetchHeroById(@Path("id") id: String): SingleHeroResponse
}