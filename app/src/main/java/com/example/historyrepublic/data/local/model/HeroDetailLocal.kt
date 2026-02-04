package com.example.historyrepublic.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.historyrepublic.domain.HeroDetail


@Entity(tableName = "hero_detail")
data class HeroDetailLocal(
    @PrimaryKey val id: String,
    val url: String
)

fun HeroDetailLocal.toUI(): HeroDetail = with(this) {
    HeroDetail(id, url)
}
