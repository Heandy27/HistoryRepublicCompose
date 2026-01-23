package com.example.historyrepublic.data.network.api

import com.example.historyrepublic.data.network.model.HeroResponse
import retrofit2.http.GET

interface HeroApi {
    @GET("api/heroes")
    suspend fun getHeros(): List<HeroResponse>
}