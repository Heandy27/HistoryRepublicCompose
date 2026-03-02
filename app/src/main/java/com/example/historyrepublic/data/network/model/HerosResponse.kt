package com.example.historyrepublic.data.network.model

import com.example.historyrepublic.data.local.model.HeroLocal

data class HerosResponse(
    val id: String,
    val nameHero: String,
    val title: String,
    val information: String,
    val image: String,
    val url: String,
    val latitude: Double,
    val longitude: Double,
    val youtubeVideo: String
)

fun List<HerosResponse>.toLocal(): List<HeroLocal> = this.map {
    it.toLocal()
}

fun HerosResponse.toLocal(): HeroLocal = with(this) {
    HeroLocal(id, nameHero, title, information, image, url, latitude, longitude, youtubeVideo)
}