package com.example.modulaiztioncoursedemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.core.DataState
import com.example.core.Logger
import com.example.core.ProgressBarState
import com.example.core.UIComponent
import com.example.hero_interactors.GetHeroes
import com.example.hero_interactors.HeroInteractors
import com.example.modulaiztioncoursedemo.ui.theme.ModulaiztionCourseDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModulaiztionCourseDemoTheme {
                val heroListViewModel:HeroListViewModel= hiltViewModel()
                HeroList(
                    state = heroListViewModel.state.value,
                )



            }
        }
    }
}


