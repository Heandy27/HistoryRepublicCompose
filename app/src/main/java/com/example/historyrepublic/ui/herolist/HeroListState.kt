package com.example.historyrepublic.ui.herolist

import com.example.historyrepublic.domain.Hero

sealed class HeroListState {
    data class Success(val heros: List<Hero>): HeroListState()
    object Loading: HeroListState()
    data class Error(val error: String): HeroListState()
}