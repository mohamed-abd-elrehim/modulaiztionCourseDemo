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
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.core.DataState
import com.example.core.Logger
import com.example.core.ProgressBarState
import com.example.core.UIComponent
import com.example.hero_domain.Hero
import com.example.hero_interactors.GetHeroes
import com.example.hero_interactors.HeroInteractors
import com.example.modulaiztioncoursedemo.ui.HeroList
import com.example.modulaiztioncoursedemo.ui.HeroListState
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
                    getHeroes = HeroInteractors.build(
                        AndroidSqliteDriver(
                            HeroInteractors.schema,
                            this,
                            HeroInteractors.dbName
                        )
                    ).getHeroes,
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
) {
    val state = remember { mutableStateOf<HeroListState>(HeroListState()) }
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

                    state.value = state.value.copy(heroesState = dataState.data ?: emptyList())
                }
                is DataState.Loading -> {
                    loading.value = dataState.progressBarState
                }
            }
        }.launchIn(this)
    }

    HeroList(state = state.value)
}
