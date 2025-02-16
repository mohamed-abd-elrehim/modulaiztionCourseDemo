package com.example.modulaiztioncoursedemo.ui

import com.example.core.domain.UiComponentsState
import com.example.hero_domain.HeroAttribute
import com.example.hero_domain.HeroFilter

sealed class HeroListEvents {

    object  GetHeroes : HeroListEvents()
    object  FilterHeroes : HeroListEvents()
    data class UpdateHeroName(val heroName: String) : HeroListEvents()
    data class UpdateHeroFilter (val heroFilter: HeroFilter):HeroListEvents()
    data class UpdateHeroPrimaryAttr(val heroAttribute: HeroAttribute):HeroListEvents()
    data class UpdateFilterDialogState(val uiComponentState: UiComponentsState):HeroListEvents()

    object RemoveHeadMessageFromQueue: HeroListEvents()




}