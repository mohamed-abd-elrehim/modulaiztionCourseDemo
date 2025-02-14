package com.example.modulaiztioncoursedemo.ui

import coil.ImageLoader
import com.example.core.ProgressBarState
import com.example.hero_domain.Hero

data class HeroDetailsState (
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heroState: Hero?= null,
    val imageLoader: ImageLoader?

)