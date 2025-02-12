package com.example.modulaiztioncoursedemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import com.example.herodetail.R
import com.example.modulaiztioncoursedemo.components.AppText
import com.example.modulaiztioncoursedemo.components.ElevationCard
import com.example.modulaiztioncoursedemo.components.Gap
import com.example.modulaiztioncoursedemo.components.HeroStatisticsCard
import com.example.modulaiztioncoursedemo.components.HeroWinColumn
import com.example.modulaiztioncoursedemo.components.LoadAsyncImage

@Composable
fun HeroDetails(
    ){


    Box(modifier = Modifier
        .background(Color.Cyan)
        .fillMaxSize()) {
        Column() {
            Box(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .height(250.dp)
            )

            AppText(
                text = "Hero Details",
                fontSize = 40.sp,
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold
            )
            AppText(
                text = "Agility",
                modifier = Modifier.padding(5.dp),
                fontSize = 20.sp,
            )
            AppText(
                text = "Ranged",
                fontSize = 15.sp,
                modifier = Modifier.padding(5.dp),
                color = Color.Gray
            )

            Gap(height = 20)
            HeroStatisticsCard()

            Gap(height = 20)
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                HeroWinColumn()
                Gap(width = 100)
                HeroWinColumn()
            }

        }


    }




}

@Composable
@Preview(showSystemUi = true)
fun HeroDetailsPreview(){
    HeroDetails()
    }