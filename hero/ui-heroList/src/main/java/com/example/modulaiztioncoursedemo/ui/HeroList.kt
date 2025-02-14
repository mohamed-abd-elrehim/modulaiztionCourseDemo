package com.example.modulaiztioncoursedemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.core.ProgressBarState
import com.example.modulaiztioncoursedemo.components.HeroListCard
import com.example.modulaiztioncoursedemo.components.HeroListToolbar

@Composable
fun HeroList(
    state: HeroListState,
    navToHeroDetail: (Int) -> Unit
) {
    val context = LocalContext.current

   Column (
       modifier = Modifier
           .background(Color.Cyan)
           .fillMaxSize()
   ){
       HeroListToolbar(


           heroName = "",
           onHeroNameChanged = { /* Handle hero name input */ },
           onExecuteSearch = { /* Handle search */ },
           onShowFilterDialog = { /* Show filter dialog */ }
       )

       Box() {
           LazyColumn(
               modifier = Modifier
                   .background(Color.Cyan)
                   .fillMaxSize()
           ) {
               items(state.heroesState.size) { index ->
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







