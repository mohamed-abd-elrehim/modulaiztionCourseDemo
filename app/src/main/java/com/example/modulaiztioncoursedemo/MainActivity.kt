package com.example.modulaiztioncoursedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.core.DataState
import com.example.core.Logger
import com.example.core.ProgressBarState
import com.example.core.UIComponent
import com.example.hero_domain.Hero
import com.example.hero_interactors.GetHeroes
import com.example.hero_interactors.HeroInteractors
import com.example.modulaiztioncoursedemo.ui.theme.ModulaiztionCourseDemoTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {

    private val logger = Logger("getHeroeTest")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModulaiztionCourseDemoTheme {
                MainScreen(
                    getHeroes = HeroInteractors.build().getHeroes,
                    logger = logger
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    getHeroes: GetHeroes,
    logger: Logger,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val heros = remember { mutableStateOf<List<Hero>>(emptyList()) }
    val loading:MutableState<ProgressBarState> = remember { mutableStateOf(ProgressBarState.Idle) }

    LaunchedEffect(Unit) {
        getHeroes.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (val uiComponent = dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            uiComponent.description?.let { logger.log(it) }
                        }
                        is UIComponent.None -> {
                            uiComponent.message?.let { logger.log(it) }
                        }
                    }
                }
                is DataState.Data -> {
                    heros.value = dataState.data ?: emptyList()
                }
                is DataState.Loading -> {
                    loading.value = dataState.progressBarState
                }
            }
        }.launchIn(this)
    }

    Box(modifier = modifier) {
        LazyColumn {
            items(heros.value.size) { hero ->
                Text( heros.value[hero].localizedName)
            }
        }
        if (loading.value is ProgressBarState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
