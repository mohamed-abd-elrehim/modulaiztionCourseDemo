package com.example.modulaiztioncoursedemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
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
        setContent {
            ModulaiztionCourseDemoTheme {
                val heroListViewModel:HeroListViewModel= hiltViewModel()
                val state = heroListViewModel.state.collectAsState()

                HeroList(state.value
                    //imageLoader = imageLoader
                )
            }
        }
    }
}


