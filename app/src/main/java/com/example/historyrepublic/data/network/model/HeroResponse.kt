package com.example.historyrepublic.data.network.model

import com.example.historyrepublic.data.local.model.HeroLocal

data class HeroResponse(
    val id: String,
    val nameHero: String,
    val title: String,
    val information: String,
    val image: String,
    val url: String
)

fun List<HeroResponse>.toLocal(): List<HeroLocal> = this.map {
    it.toLocal()
}

fun HeroResponse.toLocal(): HeroLocal = with(this) {
    HeroLocal(id, nameHero, title, information, image, url)
}