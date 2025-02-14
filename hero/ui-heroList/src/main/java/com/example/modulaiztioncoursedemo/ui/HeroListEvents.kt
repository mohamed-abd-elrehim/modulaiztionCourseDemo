package com.example.modulaiztioncoursedemo.ui

import com.example.hero_interactors.GetHeroes

sealed class HeroListEvents {

    object  GetHeroes : HeroListEvents()
    object  FilterHeroes : HeroListEvents()
    data class UpdateHeroName(val heroName: String) : HeroListEvents()

}