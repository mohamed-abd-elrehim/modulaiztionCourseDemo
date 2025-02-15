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
import com.example.core.UiComponentsState
import com.example.hero_domain.HeroFilter
import com.example.modulaiztioncoursedemo.components.HeroListCard
import com.example.modulaiztioncoursedemo.components.HeroListFilterPopup
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
           onShowFilterDialog = {
               events(HeroListEvents.UpdateFilterDialogState(UiComponentsState.Show))
           },

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
                           hero = state.filterHerosState[index],
                           imageLoader = imageLoader,
                           context = context,
                           onSelectHero = { heroId ->
                               navToHeroDetail(heroId)
                           }
                       )
                   }
               }
           }
           if (state.uiComponentsState is UiComponentsState.Show) {
               HeroListFilterPopup(
                   heroFilter = state.heroFilter,
                   onUpdateHeroFilter = { heroFilter ->
                       events(HeroListEvents.UpdateHeroFilter(heroFilter))
                   },
                   attributeFilter = state.primaryAttribute,
                   onUpdateAttributeFilter = { heroAttribute ->
                       events(HeroListEvents.UpdateHeroPrimaryAttr(heroAttribute))
                   },
                   onCloseDialog = {
                       events(HeroListEvents.UpdateFilterDialogState(UiComponentsState.Hide))
                   })
           }
           if (state.progressBarState is ProgressBarState.Loading) {
               CircularProgressIndicator(
                   modifier = Modifier.align(Alignment.Center)
               )
           }
       }

   }


}







