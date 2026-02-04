package com.example.historyrepublic.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.historyrepublic.data.local.model.HeroDetailLocal

@Dao
interface HeroDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserHeroDetail(detail: HeroDetailLocal)

    @Query("SELECT * FROM hero_detail WHERE id = :heroId")
    suspend fun getHeroDetail(heroId: String): HeroDetailLocal

}