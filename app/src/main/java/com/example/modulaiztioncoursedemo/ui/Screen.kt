package com.example.modulaiztioncoursedemo.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen (
    val route:String,
    val arguments:List<NamedNavArgument>
){
     object HeroListScreen : Screen(
        route = "HeroList",
        arguments = emptyList()
    )

    object HeroDetailScreen : Screen(
        route = "HeroDetails",
        arguments = listOf(
            navArgument("heroId"){
                type = NavType.IntType
            }
        )
    )


}
