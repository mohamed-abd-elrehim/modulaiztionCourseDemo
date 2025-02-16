package com.example.modulaiztioncoursedemo.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDialog(
    modifier: Modifier=Modifier,
    onDismiss: () -> Unit,
    showDialog: Boolean,
    content: @Composable () -> Unit
) {
    if (showDialog) {

        BasicAlertDialog(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp)),
            properties = DialogProperties(
                dismissOnClickOutside = false

            ),
            onDismissRequest = onDismiss,
            content = {
                ElevationCard(
                    modifier = Modifier
                        .then(modifier)
                        .clip(RoundedCornerShape(16.dp)), // Rounded corners
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Soft shadow
                ) {
                    content()
                }

            }
        )
    }
}
