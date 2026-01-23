package com.example.historyrepublic.data.network

import com.example.historyrepublic.data.network.model.HeroResponse

interface NetworkDataSource {
    suspend fun getHeros(): List<HeroResponse>
}