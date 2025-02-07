package com.example.hero_interactors

import HeroServiceImpl
import com.example.hero_datasource.network.service.IHeroService

data class HeroInteractors(
    val getHeroes: GetHeroes,
) {
    companion object Factory {
        fun build(): HeroInteractors {
            val service = IHeroService.create()
            return HeroInteractors(
                getHeroes = GetHeroes(service = service)
            )
        }
    }
}
