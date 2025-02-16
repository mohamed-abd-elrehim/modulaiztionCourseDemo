package com.example.modulaiztioncoursedemo.ui

import coil.ImageLoader
import com.example.core.domain.ProgressBarState
import com.example.core.domain.Queue
import com.example.core.domain.UIComponent
import com.example.core.domain.UiComponentsState
import com.example.hero_domain.Hero

data class HeroDetailsState (
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heroState: Hero?= null,
    val imageLoader: ImageLoader?,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
    val alertDialogState: Boolean = false,






)