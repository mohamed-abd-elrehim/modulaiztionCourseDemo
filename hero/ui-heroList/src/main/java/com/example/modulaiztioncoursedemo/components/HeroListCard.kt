package com.example.modulaiztioncoursedemo.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import com.example.hero_domain.Hero
import kotlin.math.round

@Composable
fun HeroListCard(
    context: Context,
    hero: Hero,
    imageLoader: ImageLoader,
    onSelectHero: (Int) -> Unit,
) {

        ElevationCard(
            modifier = Modifier
                .padding(10.dp)
                .clickable { onSelectHero(hero.id) } // Ensure you pass the hero's ID
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .fillMaxWidth()
                ) {
                    // Hero Image
                    LoadAsyncImage(
                        imageUrl = hero.img,
                        imageTitle = hero.localizedName,
                        modifier = Modifier
                            .width(150.dp),
                        imageLoader = imageLoader,
                        context = context
                    )

                    Gap(width = 20)

                    // Hero Details
                    Column(
                        modifier = Modifier.weight(1f) // Take up available space
                            .align(Alignment.CenterVertically)
                    ) {
                        AppText(
                            text = hero.localizedName,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Gap(height = 5)
                        AppText(
                            text = hero.primaryAttribute.uiValue,
                            fontSize = 15.sp,
                        )
                    }
                    // Using remember in list item does not behave correctly? //                val proWR: Int = remember{round(hero.proWins.toDouble() / hero.proPick.toDouble() * 100).toInt()}

                    // Pro WR percentage moved to the end
                    val proWR: Int =
                        round(hero.proWins.toDouble() / hero.proPick.toDouble() * 100).toInt()

                    AppText(
                        text = "${proWR}%",
                        fontSize = 15.sp,
                        color = if (proWR > 50) Color.Blue else Color.Red,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
