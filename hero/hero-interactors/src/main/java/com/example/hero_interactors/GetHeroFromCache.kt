package com.example.hero_interactors

import com.example.core.DataState
import com.example.core.ProgressBarState
import com.example.core.UIComponent
import com.example.hero_datasource.cache.cache_service.IHeroCacheService
import com.example.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeroFromCache(
    private val cache: IHeroCacheService,
    ) {
    fun execute(id: Int):  Flow<DataState<Hero>> = flow {
        emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

        try {
            // get hero from cache
            val hero = cache.getHero(id) ?: throw Exception("Hero with id $id not found")
            emit(DataState.Data(hero))

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
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}



