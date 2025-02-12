package com.example.modulaiztioncoursedemo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun HeroWinColumn (){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally


    ){
        AppText(
            text = "Win Rate",
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        AppText(
            text = "50%",
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = Color.Green
        )
    }
}