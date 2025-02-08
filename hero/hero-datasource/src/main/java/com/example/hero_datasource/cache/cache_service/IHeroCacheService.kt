package com.example.hero_datasource.cache.cache_service

import app.cash.sqldelight.db.SqlDriver
import com.example.hero_datasource.cache.HeroDatabase
import com.example.hero_domain.Hero


interface IHeroCacheService {

    suspend fun getHero(id: Int): Hero?

    suspend fun removeHero(id: Int)

    suspend fun selectAll(): List<Hero>

    suspend fun insert(hero: Hero)

    suspend fun insert(heros: List<Hero>)

    suspend fun searchByName(localizedName: String): List<Hero>

    suspend fun searchByAttr(primaryAttr: String): List<Hero>

    suspend fun searchByAttackType(attackType: String): List<Hero>

    // Can select multiple roles
    suspend fun searchByRole(
        carry: Boolean = false,
        escape: Boolean = false,
        nuker: Boolean = false,
        initiator: Boolean = false,
        durable: Boolean = false,
        disabler: Boolean = false,
        jungler: Boolean = false,
        support: Boolean = false,
        pusher: Boolean = false,
    ): List<Hero>

    companion object Factory {
        fun build(sqlDriver: SqlDriver): IHeroCacheService {
            return HeroCacheServiceImpl(HeroDatabase(sqlDriver))
        }
       val schema= HeroDatabase.Schema
       val dbName: String = "heros.db"
    }

}