package com.example.modulaiztioncoursedemo.ui

import coil.ImageLoader
import com.example.core.ProgressBarState
import com.example.hero_domain.Hero

data class HeroListState(
     val progressBarState: ProgressBarState = ProgressBarState.Idle,
     val heroesState: List<Hero> = emptyList(),
     val imageLoader: ImageLoader?,

     val filterHerosState:List<Hero> = emptyList(),
     val heroName: String = "",


     )
