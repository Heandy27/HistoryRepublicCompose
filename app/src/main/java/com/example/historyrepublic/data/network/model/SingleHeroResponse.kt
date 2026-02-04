package com.example.historyrepublic.data.network.model

import com.example.historyrepublic.data.local.model.HeroDetailLocal
import com.example.historyrepublic.data.local.model.HeroLocal

data class SingleHeroResponse (
    val id: String,
    val url: String
)


fun SingleHeroResponse.toLocal(): HeroDetailLocal = with(this) {
    HeroDetailLocal(id, url)
}



