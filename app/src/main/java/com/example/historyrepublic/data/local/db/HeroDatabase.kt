package com.example.historyrepublic.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.historyrepublic.data.local.model.HeroDetailLocal
import com.example.historyrepublic.data.local.model.HeroLocal

@Database(entities = [
    HeroLocal::class,
    HeroDetailLocal::class
                     ], version = 6)
abstract class HeroDatabase: RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroDetailDao(): HeroDetailDao
}