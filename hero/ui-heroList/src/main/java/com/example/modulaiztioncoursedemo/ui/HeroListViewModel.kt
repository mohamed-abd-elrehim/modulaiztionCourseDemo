package com.example.modulaiztioncoursedemo.ui


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.DataState
import com.example.core.Logger
import com.example.core.UIComponent
import com.example.hero_interactors.GetHeroes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val getHeroes: GetHeroes,
): ViewModel() {

    private val logger = Logger("HeroListViewModel")
    val state =  mutableStateOf<HeroListState>(HeroListState())

    init {
        getHeroes()

    }


    private  fun getHeroes(){
        getHeroes.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (val uiComponent = dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            uiComponent.description?.let { logger.log(it) }
                        }
                        is UIComponent.None -> {
                            uiComponent.message?.let { logger.log(it) }
                        }
                    }
                }
                is DataState.Data -> {

                    state.value = state.value.copy(heroesState = dataState.data ?: emptyList())
                }
                is DataState.Loading -> {
                    state.value = state.value.copy( progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn( viewModelScope)
    }




}