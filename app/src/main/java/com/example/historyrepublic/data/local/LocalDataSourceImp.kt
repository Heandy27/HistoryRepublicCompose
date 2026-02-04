package com.example.historyrepublic.data.local

import com.example.historyrepublic.data.local.db.HeroDao
import com.example.historyrepublic.data.local.db.HeroDetailDao
import com.example.historyrepublic.data.local.model.HeroDetailLocal
import com.example.historyrepublic.data.local.model.HeroLocal
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor( private val dao: HeroDao, private val daoDetail: HeroDetailDao): LocalDataSource {
    override suspend fun getHeros(): List<HeroLocal> {
        return dao.getHeros()
    }

    override suspend fun insertHeros(heros: List<HeroLocal>) {
        dao.insertHeros(heros)
    }

    override suspend fun getHeroDetail(id: String): HeroDetailLocal {
        return daoDetail.getHeroDetail(id)
    }

    override suspend fun insertHeroDetail(detail: HeroDetailLocal) {
        daoDetail.inserHeroDetail(detail)
    }


}