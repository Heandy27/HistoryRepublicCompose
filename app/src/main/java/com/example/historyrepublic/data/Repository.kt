package com.example.historyrepublic.data

import com.example.historyrepublic.domain.Hero

interface Repository {
    suspend fun getHeroes(): List<Hero>
}