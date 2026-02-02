package com.example.historyrepublic.data.network

import com.example.historyrepublic.data.network.api.HeroApi
import com.example.historyrepublic.data.network.model.HerosResponse
import com.example.historyrepublic.data.network.model.SingleHeroResponse
import javax.inject.Inject

class NetworkDataSourceImp @Inject constructor(private val api: HeroApi): NetworkDataSource {
    override suspend fun getHeros(): List<HerosResponse> {
        return api.getHeros()
    }

    override suspend fun fetchHeroById(id: String): SingleHeroResponse {
        return api.fetchHeroById(id)
    }
}