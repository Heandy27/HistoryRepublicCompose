package com.example.historyrepublic.data.network

import com.example.historyrepublic.data.network.api.HeroApi
import com.example.historyrepublic.data.network.model.HeroResponse
import javax.inject.Inject

class NetworkDataSourceImp @Inject constructor(private val api: HeroApi): NetworkDataSource {
    override suspend fun getHeros(): List<HeroResponse> {
        return api.getHeros()
    }
}