package com.example.modulaiztioncoursedemo.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import coil.ImageLoader
import com.example.core.DataState
import com.example.core.Logger
import com.example.core.ProgressBarState
import com.example.core.UIComponent
import com.example.hero_interactors.GetHeroes
import com.example.hero_interactors.HeroInteractors
import com.example.modulaiztioncoursedemo.ui.theme.ModulaiztionCourseDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //@Inject lateinit var imageLoader:ImageLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModulaiztionCourseDemoTheme(
            ) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination =Screen.HeroListScreen.route,
                    modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()),
                            builder = {
                        addHeroList(navController)
                        addHeroDetail()
                    }
                )


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
        val viewModel: HeroDetailsViewModel = hiltViewModel()
        val state = viewModel.state.collectAsState()
        HeroDetails(state.value)
    }
}


