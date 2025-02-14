package com.example.hero_interactors

import app.cash.sqldelight.db.SqlDriver
import com.example.hero_datasource.cache.cache_service.IHeroCacheService
import com.example.hero_datasource.network.service.IHeroService

data class HeroInteractors(
    val getHeroes: GetHeroes,
    val getHeroFromCache: GetHeroFromCache
) {
    companion object Factory {
        fun build(sqlDriver: SqlDriver): HeroInteractors {
            val service = IHeroService.create()
            val cache = IHeroCacheService.build(sqlDriver)
            return HeroInteractors(
                getHeroes = GetHeroes(service = service, cache = cache),
                getHeroFromCache = GetHeroFromCache(cache = cache)
            )
        }

        val schema = IHeroCacheService.schema

        val dbName: String = IHeroCacheService.dbName
    }
}
