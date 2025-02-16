package com.example.modulaiztioncoursedemo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.CustomSharedList

@Composable
fun HeroStatisticsCard(
    list: List<CustomSharedList>
) {

    ElevationCard (
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
    ) {

        AppText(
            text = "Base Stats",
            fontSize = 25.sp,
            modifier = Modifier.padding(10.dp)
        )
        Gap(height = 10)
        if (list != null) {
            for (item in list) {
                CardRow(
                    column1Name = item.first,
                    column1Value = item.second,
                    column2Name = item.third,
                    column2Value = item.fourth
                )

            }
        }

    }
}

@Composable
fun CardRow(
    column1Name: String ,
    column1Value: String,
    column2Name: String ,
    column2Value: String
) {
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
            CardColumn(
                columnName = column1Name,
                columnValue = column1Value
            )
        }

        // Second column
        Box(
            modifier = Modifier
                .weight(1f), // Equal weight
        ) {
            CardColumn(
                columnName = column2Name,
                columnValue = column2Value
            )
        }
    }


}

@Composable
fun CardColumn(
    columnName: String ,
    columnValue: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppText(
            text = "$columnName:",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 5.dp)
        )
        AppText(
            text = columnValue,
            fontSize = 15.sp,
            modifier = Modifier.padding(end=5.dp)
        )

    }

}
