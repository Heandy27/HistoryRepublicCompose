package com.example.historyrepublic.data.network

import com.example.historyrepublic.data.network.model.HerosResponse
import com.example.historyrepublic.data.network.model.SingleHeroResponse

interface NetworkDataSource {
    suspend fun getHeros(): List<HerosResponse>
    suspend fun fetchHeroById(id: String): SingleHeroResponse
}