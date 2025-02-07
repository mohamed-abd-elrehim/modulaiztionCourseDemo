package com.example.hero_interactors

import HeroServiceImpl

data class HeroInteractors(
    val getHeroes: GetHeroes,
) {
    companion object Factory {
        fun build(): HeroInteractors {
            val service = HeroServiceImpl()
            return HeroInteractors(
                getHeroes = GetHeroes(service = service)
            )
        }
    }
}
