package com.example.historyrepublic.data.local

import com.example.historyrepublic.data.local.model.HeroDetailLocal
import com.example.historyrepublic.data.local.model.HeroLocal

interface LocalDataSource {
    suspend fun getHeros(): List<HeroLocal>
    suspend fun insertHeros(heros: List<HeroLocal>)

    suspend fun getHeroDetail(id: String): HeroDetailLocal
    suspend fun insertHeroDetail(detail: HeroDetailLocal)
}