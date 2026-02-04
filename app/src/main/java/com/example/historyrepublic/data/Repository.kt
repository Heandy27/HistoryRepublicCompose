package com.example.historyrepublic.data

import com.example.historyrepublic.data.network.model.SingleHeroResponse
import com.example.historyrepublic.domain.Hero
import com.example.historyrepublic.domain.HeroDetail

interface Repository {
    suspend fun getHeroes(): List<Hero>
    suspend fun fetchHeroById(id: String): HeroDetail
}