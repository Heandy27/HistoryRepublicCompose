package com.example.historyrepublic.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.historyrepublic.data.local.model.HeroLocal

@Dao
interface HeroDao {

    // Heroes
    @Query("Select * from heros")
    fun getHeros(): List<HeroLocal>

    @Insert
    fun insertHeros(heros: List<HeroLocal>)
}