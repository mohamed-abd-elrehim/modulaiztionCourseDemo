package com.example.modulaiztioncoursedemo.ui

import coil.ImageLoader
import com.example.core.domain.ProgressBarState
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent
import com.example.core.domain.UiComponentsState
import com.example.hero_domain.Hero
import com.example.hero_domain.HeroAttribute
import com.example.hero_domain.HeroFilter

data class HeroListState(
     val progressBarState: ProgressBarState = ProgressBarState.Idle,
     val heroesState: List<Hero> = emptyList(),
     val imageLoader: ImageLoader?,

     val filterHerosState:List<Hero> = emptyList(),
     val heroName: String = "",

     val heroFilter: HeroFilter = HeroFilter.Hero(),
     val primaryAttribute: HeroAttribute = HeroAttribute.Unknown,

     val uiComponentsState: UiComponentsState = UiComponentsState.Hide,


     val alertDialogState: Boolean = false,



     val errorQueue: Queue<UIComponent> = Queue(mutableListOf())



     )
