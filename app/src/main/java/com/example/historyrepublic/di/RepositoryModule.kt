package com.example.historyrepublic.di

import com.example.historyrepublic.data.Repository
import com.example.historyrepublic.data.RepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(repositoryImp: RepositoryImp): Repository {
        return repositoryImp
    }
}