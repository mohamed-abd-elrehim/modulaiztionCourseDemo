package com.example.modulaiztioncoursedemo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.core.DataState
import com.example.core.Logger
import com.example.core.UIComponent
import com.example.hero_interactors.GetHeroFromCache
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HeroDetailsViewModel @Inject constructor(
    private val getHeroFromCache: GetHeroFromCache,
    private val imageLoader: ImageLoader,
    private val savedStateHandle: SavedStateHandle,
    @Named("heroDetailsLogger") private val logger: Logger,
) : ViewModel()
{

    private val _state = MutableStateFlow(HeroDetailsState(imageLoader = imageLoader))
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("heroId").let {heroId ->
            if (heroId != null)
            onTriggerEvent(HeroDetailsEvents.GetHeroFromCache(heroId))
            else{
                logger.log("heroId is null")
            }

        }

    }

    fun onTriggerEvent(event: HeroDetailsEvents) {
        when (event) {
            is HeroDetailsEvents.GetHeroFromCache -> {
                getHeroFromCache(event.id)
            }
        }

    }

    private fun getHeroFromCache(id : Int) {
        getHeroFromCache.execute(id).onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    handelResponse(dataState.uiComponent)
                }

                is DataState.Data -> {
                    _state.value =_state.value.copy (heroState = dataState.data?: null)
                }
                is DataState.Loading -> {
                    _state.value = _state.value.copy( progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn( viewModelScope)


    }
    private fun handelResponse(uiComponent: UIComponent) {
        when (uiComponent) {
            is UIComponent.Dialog -> {
                uiComponent.description?.let { logger.log(it) }

            }

            is UIComponent.None -> {
                uiComponent.message?.let { logger.log(it) }

            }

        }
    }


}