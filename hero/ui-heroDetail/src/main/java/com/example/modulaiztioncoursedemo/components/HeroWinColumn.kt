package com.example.modulaiztioncoursedemo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun HeroWinColumn (
    color: Color = Color.Blue,
    columnName: String ,
    columnValue: String
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally


    ){
        AppText(
            text = columnName,
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )

        AppText(
            text = "${columnValue}%",
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = if (columnValue.toDouble() > 50) Color.Blue else Color.Black
        )
    }
}