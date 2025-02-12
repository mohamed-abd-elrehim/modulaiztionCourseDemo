package com.example.modulaiztioncoursedemo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import com.example.core.ProgressBarState
import com.example.modulaiztioncoursedemo.components.HeroListCard

@Composable
fun HeroList (
    state: HeroListState,
    //imageLoader: ImageLoader,
){
    val context = LocalContext.current
    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            items(state.heroesState.size) { hero ->
                state.imageLoader?.let {
                    HeroListCard( hero = state.heroesState[hero],
                        imageLoader = it,
                        context =context ,
                        onSelectHero = {}
                    )
                }
            }
        }
        if (state.progressBarState is ProgressBarState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

