package com.example.historyrepublic.data.local

import com.example.historyrepublic.data.local.db.HeroDao
import com.example.historyrepublic.data.local.model.HeroLocal
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor( private val dao: HeroDao): LocalDataSource {
    override suspend fun getHeros(): List<HeroLocal> {
        return dao.getHeros()
    }

    override suspend fun insertHeros(heros: List<HeroLocal>) {
        dao.insertHeros(heros)
    }
}