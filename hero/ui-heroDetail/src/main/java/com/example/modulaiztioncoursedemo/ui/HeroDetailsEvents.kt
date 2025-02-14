package com.example.modulaiztioncoursedemo.ui

sealed class HeroDetailsEvents {
    data class  GetHeroFromCache(
        val id: Int,
    ) : HeroDetailsEvents()
}