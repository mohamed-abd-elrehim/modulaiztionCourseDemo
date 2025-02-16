package com.example.hero_interactors

import com.example.core.domain.DataState
import com.example.core.domain.ProgressBarState
import com.example.core.domain.UIComponent
import com.example.hero_datasource.cache.cache_service.IHeroCacheService
import com.example.hero_datasource.network.service.IHeroService
import com.example.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeroes(
    private val service: IHeroService,
    private val cache: IHeroCacheService,
) {
    fun execute(): Flow<DataState<List<Hero>>> = flow {
        emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

        try {
            // get heroes from network
            val heroes = service.getHerosStats()
            // insert heroes from network into cache
            cache.insert(heroes)
            //emit data from cache
            val heroesFromCache = cache.selectAll()
           emit(DataState.Data(heroesFromCache))

            throw Exception("some error")

        } catch (e: Exception) {

            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "An unknown error occurred"
                    )
                )
            )
        } finally {
            println("finally")
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}
