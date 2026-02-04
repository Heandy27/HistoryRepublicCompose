package com.example.historyrepublic.di
import android.content.Context
import androidx.room.Room
import com.example.historyrepublic.data.Repository
import com.example.historyrepublic.data.RepositoryImp
import com.example.historyrepublic.data.local.LocalDataSource
import com.example.historyrepublic.data.local.LocalDataSourceImp
import com.example.historyrepublic.data.local.db.HeroDao
import com.example.historyrepublic.data.local.db.HeroDatabase
import com.example.historyrepublic.data.local.db.HeroDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideSuperDatabase(@ApplicationContext context: Context): HeroDatabase {
        return Room.databaseBuilder(
            context,
            HeroDatabase::class.java, "database-name"
        )
            // Borra los datos y creas de nuevo asi cuando modifiques la tabla se borre y cree la nueva
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    fun provideSuperheroDao(db: HeroDatabase): HeroDao {
        return db.heroDao()
    }

    @Provides
    fun provideLocalDataSource(localDataSourceImp: LocalDataSourceImp): LocalDataSource {
        return localDataSourceImp
    }

    @Provides
    fun provideHeroDetailDao(db: HeroDatabase): HeroDetailDao {
        return db.heroDetailDao()
    }
}