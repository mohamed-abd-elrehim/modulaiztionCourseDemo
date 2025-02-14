package com.example.modulaiztioncoursedemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.core.ProgressBarState
import com.example.modulaiztioncoursedemo.components.HeroListCard
import com.example.modulaiztioncoursedemo.components.HeroListToolbar

@Composable
fun HeroList(
    state: HeroListState,
    events: (HeroListEvents) -> Unit,
    navToHeroDetail: (Int) -> Unit
) {
    val context = LocalContext.current

   Column (
       modifier = Modifier
           .background(Color.Cyan)
           .fillMaxSize()
   ){
       HeroListToolbar(


           heroName = state.heroName,
           onHeroNameChanged = {
               events(HeroListEvents.UpdateHeroName(it))
           },
           onExecuteSearch = {
               events(HeroListEvents.FilterHeroes)
           },
           onShowFilterDialog = { /* Show filter dialog */ }
       )

       Box() {
           LazyColumn(
               modifier = Modifier
                   .background(Color.Cyan)
                   .fillMaxSize()
           ) {
               items(state.filterHerosState.size) { index ->
                   state.imageLoader?.let { imageLoader ->
                       HeroListCard(
                           hero = state.heroesState[index],
                           imageLoader = imageLoader,
                           context = context,
                           onSelectHero = { heroId ->
                               navToHeroDetail(heroId)
                           }
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


}







