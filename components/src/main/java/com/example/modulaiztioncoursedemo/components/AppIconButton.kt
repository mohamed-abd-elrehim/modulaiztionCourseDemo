package com.example.modulaiztioncoursedemo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AppIconButton(
    icon: ImageVector = Icons.Default.Close,
    modifier: Modifier = Modifier, // Allow custom modifier
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .then(modifier) // Apply custom modifier while keeping default styles
            .background(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(50)
            )
            .padding(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Icon Button",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
