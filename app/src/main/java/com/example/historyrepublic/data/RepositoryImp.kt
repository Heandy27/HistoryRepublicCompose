package com.example.historyrepublic.data

import com.example.historyrepublic.data.local.LocalDataSource
import com.example.historyrepublic.data.local.model.toUI
import com.example.historyrepublic.data.network.NetworkDataSource
import com.example.historyrepublic.data.network.model.SingleHeroResponse
import com.example.historyrepublic.data.network.model.toLocal
import com.example.historyrepublic.domain.Hero
import com.example.historyrepublic.domain.HeroDetail
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
): Repository {

    override suspend fun getHeroes(): List<Hero> {
        val localHeros = localDataSource.getHeros()

        if (localHeros.isEmpty()) {
            val  remoteHeros = networkDataSource.getHeros()
            localDataSource.insertHeros(remoteHeros.toLocal())
        }

        return localDataSource.getHeros().toUI()

    }

    override suspend fun fetchHeroById(id: String): HeroDetail {
       val localHeroDetail = localDataSource.getHeroDetail(id)

        if( localHeroDetail == null) {
            val remoteHeroDetail = networkDataSource.fetchHeroById(id)

            localDataSource.insertHeroDetail(remoteHeroDetail.toLocal())
        }
        return localDataSource.getHeroDetail(id).toUI()
    }

}