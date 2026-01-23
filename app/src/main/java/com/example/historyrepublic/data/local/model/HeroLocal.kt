package com.example.historyrepublic.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.historyrepublic.data.network.model.HeroResponse
import com.example.historyrepublic.domain.Hero

@Entity(tableName = "heros")
data class HeroLocal(
    @PrimaryKey @ColumnInfo("id") val id: String,
    @ColumnInfo("nameHero") val nameHero: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("information") val information: String,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("url") val url: String
)

fun List<HeroLocal>.toUI(): List<Hero> = this.map {
    it.toUI()
}

fun HeroLocal.toUI(): Hero = with(this) {
    Hero(id, nameHero, title, information, image, url)
}
