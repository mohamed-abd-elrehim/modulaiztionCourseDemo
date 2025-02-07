package com.example.hero_datasource.network.service

import HeroServiceImpl
import com.example.hero_domain.Hero
import io.ktor.client.HttpClient
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json

interface IHeroService {
    suspend fun getHerosStats (): List<Hero>

    companion object {
        fun create(): HeroServiceImpl {
            val client = HttpClient(CIO) {

                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                    })
                }
            }

            return HeroServiceImpl(client)
        }
    }
}