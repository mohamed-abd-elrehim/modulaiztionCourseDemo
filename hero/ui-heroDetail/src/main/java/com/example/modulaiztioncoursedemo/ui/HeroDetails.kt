package com.example.modulaiztioncoursedemo.ui

import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import com.example.core.domain.CustomSharedList
import com.example.core.domain.UIComponent
import com.example.hero_domain.Hero
import com.example.hero_domain.maxAttackDmg
import com.example.hero_domain.minAttackDmg
import com.example.herodetail.R
import com.example.modulaiztioncoursedemo.components.AppAlertDialog
import com.example.modulaiztioncoursedemo.components.AppText
import com.example.modulaiztioncoursedemo.components.Gap
import com.example.modulaiztioncoursedemo.components.HeroStatisticsCard
import com.example.modulaiztioncoursedemo.components.HeroWinColumn
import com.example.modulaiztioncoursedemo.components.LoadAsyncImage
import kotlin.math.round

@Composable
fun HeroDetails(
    state: HeroDetailsState,
    events: (HeroDetailsEvents) -> Unit,
) {
    val context = LocalContext.current
    val imageLoader = state.imageLoader
    state.heroState?.let { hero ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan)
        ) {


            LazyColumn {
                item { HeroImage(hero.img, hero.localizedName, imageLoader, context) }
                item { HeroBasicDetails(hero, imageLoader, context) }
                item { HeroStatsSection(hero) }
                item { HeroWinStats(hero) }


            }
            if (state.errorQueue.isNotEmpty()) {

                state.errorQueue.peek()?.let { uiComponent ->
                    if (uiComponent is UIComponent.Dialog) {
                        AppAlertDialog (
                            showDialog = true,
                            title = uiComponent.title,
                            description = uiComponent.description,
                            onRemoveHeadFromQueue = {
                                events(HeroDetailsEvents.RemoveHeadMessageFromQueue)
                            },
                        )

                    }

                }




            }



        }
    }
}


@Composable
fun HeroImage(heroImgUrl:String,heroName:String, imageLoader: ImageLoader?, context: Context,modifier: Modifier =  Modifier
    .fillMaxWidth()
    .height(400.dp)) {
    imageLoader?.let {
        LoadAsyncImage(
            imageUrl = heroImgUrl,
            imageTitle = heroName,
            modifier = modifier,
            imageLoader = imageLoader,
            context = context
        )
    }
}

@Composable
fun HeroBasicDetails(hero: Hero,imageLoader: ImageLoader?, context: Context) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ){

            AppText(
                text = hero.localizedName,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            HeroImage(hero.icon,hero.localizedName,imageLoader, context,
                modifier = Modifier
                    .background(Color.Transparent)
                    .width(50.dp)
                    .height(50.dp)
            )

        }

        AppText(
            text = hero.primaryAttribute.uiValue,
            modifier = Modifier.padding(bottom = 10.dp),
            fontSize = 20.sp
        )
        AppText(
            text = hero.attackType.uiValue,
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 10.dp)

        )
    }
}

@Composable
fun HeroStatsSection(hero: Hero) {
    Gap(height = 20)

    val health = remember { round((hero.baseHealth + hero.baseStr * 20)).toInt() }
    val atkMin = remember { hero.minAttackDmg() }
    val atkMax = remember { hero.maxAttackDmg() }

    val statsList = listOf(
        CustomSharedList(
            first = stringResource(id = R.string.strength),
            second = "${hero.baseStr} + ${hero.strGain}",
            third = stringResource(id = R.string.agility),
            fourth = "${hero.baseAgi} + ${hero.agiGain}"
        ),
        CustomSharedList(
            first = stringResource(id = R.string.intelligence),
            second = "${hero.baseInt} + ${hero.intGain}",
            third = stringResource(id = R.string.health),
            fourth = "$health"
        ),
        CustomSharedList(
            first = stringResource(id = R.string.attack_range),
            second = "${hero.attackRange}",
            third = stringResource(id = R.string.projectile_speed),
            fourth = "${hero.projectileSpeed}"
        ),
        CustomSharedList(
            first = stringResource(id = R.string.move_speed),
            second = "${hero.moveSpeed}",
            third = stringResource(id = R.string.attack_dmg),
            fourth = "$atkMin - $atkMax"
        )
    )

    HeroStatisticsCard(list = statsList)


}

@Composable
fun HeroWinStats(hero: Hero) {
    Gap(height = 20)
    val proWinPercentage = remember {
        round((hero.proWins.toDouble() / (hero.proPick.toDouble())) * 100).toInt()
    }
    val turboWinPercentage = remember {
        round((hero.turboWins.toDouble() / (hero.turboPicks.toDouble())) * 100).toInt()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HeroWinColumn(
            columnName = "Pro Wins",
            columnValue = "$proWinPercentage"
        )
        Gap(width = 100)
        HeroWinColumn(
            columnName = "Turbo Wins",
            columnValue = "$turboWinPercentage"
        )
    }


}

