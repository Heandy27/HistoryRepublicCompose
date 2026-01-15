package com.example.historyrepublic.data

import com.example.historyrepublic.data.local.LocalDataSource
import com.example.historyrepublic.data.network.NetworkDataSource
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
): Repository {
}