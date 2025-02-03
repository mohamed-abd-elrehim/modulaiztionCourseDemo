package com.example.hero_datasource.network.service

import com.example.hero_domain.Hero

interface IHeroService {
    suspend fun getHerosStats (): List<Hero>
}