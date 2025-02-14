package com.example.modulaiztioncoursedemo.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.core.DataState
import com.example.core.Logger
import com.example.core.UIComponent
import com.example.hero_interactors.FilterHeros
import com.example.hero_interactors.GetHeroes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val getHeroes: GetHeroes,
    @Named("heroListLogger")  private val logger: Logger,
    private val filterHeros: FilterHeros,
    private val imageLoader: ImageLoader,
): ViewModel() {

    //val state =  mutableStateOf<HeroListState>(HeroListState(imageLoader = imageLoader))

    private val _state = MutableStateFlow(HeroListState(imageLoader = imageLoader))
    val state = _state.asStateFlow()

    init {
       onTriggerEvent(HeroListEvents.GetHeroes)

    }

    fun onTriggerEvent(event: HeroListEvents){
        when(event){
            is HeroListEvents.GetHeroes -> {
                getHeroes()
            }
            is HeroListEvents.FilterHeroes -> {
                filterHeroes()
            }
            is HeroListEvents.UpdateHeroName -> {
                updateHeroName(event.heroName)
            }
        }
    }


    private fun updateHeroName (heroName:String){
        _state.value = _state.value.copy(heroName = heroName)
    }

    private fun filterHeroes(){
        val filteredList = filterHeros.execute(
            current = _state.value.heroesState,
            heroName = _state.value.heroName,
            heroFilter = _state.value.heroFilter,
            attributeFilter = _state.value.primaryAttribute
        )

        _state.value = _state.value.copy(filterHerosState = filteredList)

    }
    private  fun getHeroes(){
        getHeroes.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    handelResponse(dataState.uiComponent)
                }

                is DataState.Data -> {
                    _state.value =_state.value.copy (heroesState = dataState.data?: emptyList())
                    filterHeroes()
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