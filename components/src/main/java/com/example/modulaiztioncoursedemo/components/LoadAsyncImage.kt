package com.example.modulaiztioncoursedemo.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun LoadAsyncImage(
    context: Context,
    imageUrl: String,
    imageTitle: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop // Default value for contentScale
) {

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = imageTitle,
        modifier = modifier,
        contentScale = contentScale
    )
}