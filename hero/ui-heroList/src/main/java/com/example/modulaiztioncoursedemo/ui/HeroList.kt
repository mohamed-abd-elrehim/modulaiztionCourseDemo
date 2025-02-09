package com.example.modulaiztioncoursedemo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.ProgressBarState
import com.example.modulaiztioncoursedemo.components.Gap
import com.example.modulaiztioncoursedemo.components.HeroListCard

@Composable
fun HeroList (
    state: HeroListState,
){
    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            items(state.heroesState.size) { hero ->
                HeroListCard( hero = state.heroesState[hero],
                    onSelectHero = {}
                )
            }
        }
        if (state.progressBarState is ProgressBarState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}