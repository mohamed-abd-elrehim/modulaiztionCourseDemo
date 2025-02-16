package com.example.modulaiztioncoursedemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modulaiztioncoursedemo.ui.theme.ModulaiztionCourseDemoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Gray.toArgb(),
                Color.Gray.toArgb() // Light scrim for contrast
            )
        )




        setContent {
            ModulaiztionCourseDemoTheme(

            ) {

                val navController = rememberNavController()
                Box(
                  ) {
                    NavHost(
                        navController = navController,
                        startDestination =Screen.HeroListScreen.route,
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding(),
                        builder = {
                            addHeroList(navController)
                            addHeroDetail()
                        }
                    )
                }



            }
        }
    }
}


fun NavGraphBuilder.addHeroList(
    navController: NavController
) {
    composable(
        route = Screen.HeroListScreen.route,
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300)
            )+ fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300)
            )+ fadeIn(animationSpec = tween(300))


        }
    ) {
        val heroListViewModel: HeroListViewModel = hiltViewModel()
        val state = heroListViewModel.state.collectAsState()

        HeroList(
            state.value,
            events = heroListViewModel::onTriggerEvent,
            navToHeroDetail = { heroId ->
                navController.navigate(
                    "${Screen.HeroDetailScreen.route}/${heroId}"
                )

            },

            //imageLoader = imageLoader
        )

    }
}
fun NavGraphBuilder.addHeroDetail() {
    composable(
        route = Screen.HeroDetailScreen.route+"/{heroId}",
        arguments = Screen.HeroDetailScreen.arguments,
        enterTransition ={
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            )+ fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        }
        ){
        val heroDetailsViewModel: HeroDetailsViewModel = hiltViewModel()
        val state = heroDetailsViewModel.state.collectAsState()
        HeroDetails(state.value,
                events = heroDetailsViewModel::onTriggerEvent
        )
    }
}


