package com.example.modulaiztioncoursedemo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeroStatisticsCard(){

    ElevationCard (
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
    ) {

        AppText(
            text = "Base ",
            fontSize = 25.sp,
            modifier = Modifier.padding(10.dp)
        )
        Gap(height = 10)

        CardRow()
        CardRow()
        CardRow()
        CardRow()




    }
}

@Composable
fun CardRow(){
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        // First column
        Box(
            modifier = Modifier
                .weight(1f), // Equal weight
        ) {
            CardColumn()
        }

        // Second column
        Box(
            modifier = Modifier
                .weight(1f), // Equal weight
        ) {
            CardColumn()
        }
    }


}

@Composable
fun CardColumn(){

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppText(
            text = "Base Attack Min" + ":",
            fontSize = 15.sp,
            modifier = Modifier.padding(end = 5.dp)
        )
        AppText(
            text = "10 + 10",
            fontSize = 15.sp,
            modifier = Modifier.padding(end=5.dp)
        )

    }

}

@Composable
@Preview(showSystemUi = true)
fun HeroStatisticsCardPreview(){
    HeroStatisticsCard()
}