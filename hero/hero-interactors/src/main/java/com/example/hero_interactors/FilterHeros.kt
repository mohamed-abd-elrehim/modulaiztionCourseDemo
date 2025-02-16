package com.example.hero_interactors

import com.example.core.domain.FilterOrder
import com.example.hero_domain.Hero
import com.example.hero_domain.HeroAttribute
import com.example.hero_domain.HeroFilter
import kotlin.math.round

class FilterHeros {

    fun execute(
        current: List<Hero>,
        heroName: String,
        heroFilter: HeroFilter,
        attributeFilter: HeroAttribute,
    ): List<Hero> {
        var filteredList: MutableList<Hero> = current.filter {
            it.localizedName.contains(heroName,ignoreCase = true)
        }.toMutableList()

        when(heroFilter){
            is HeroFilter.Hero -> {
                when(heroFilter.order){
                    is FilterOrder.Descending -> {
                        filteredList.sortByDescending { it.localizedName  }
                    }
                    is FilterOrder.Ascending -> {
                        filteredList.sortBy { it.localizedName  }
                    }
                }
            }
            is HeroFilter.ProWins -> {
                when(heroFilter.order){
                    is FilterOrder.Descending -> {
                        filteredList.sortByDescending {
                            getWinRate(it.proWins, it.proPick)
                        }
                    }
                    is FilterOrder.Ascending -> {
                        filteredList.sortBy {
                            getWinRate(it.proWins, it.proPick)
                        }
                    }
                }
            }
        }

        when(attributeFilter){
            is HeroAttribute.Strength -> {
                filteredList = filteredList.filter { it.primaryAttribute is HeroAttribute.Strength }.toMutableList()
            }
            is HeroAttribute.Agility -> {
                filteredList = filteredList.filter { it.primaryAttribute is HeroAttribute.Agility }.toMutableList()
            }
            is HeroAttribute.Intelligence -> {
                filteredList = filteredList.filter { it.primaryAttribute is HeroAttribute.Intelligence }.toMutableList()
            }
            is HeroAttribute.Unknown -> {
                // do not filter
            }
        }



        return filteredList


    }


}

private fun getWinRate(proWins: Int, proPick: Int):Int {

    return if(proPick <= 0){ // can't divide by 0
        0
    }
    else {
        val winRate: Int = round(proWins.toDouble() / proPick.toDouble() * 100).toInt()
        winRate
    }
}