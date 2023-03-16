package org.heet.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.heet.ui.theme.White700

@Composable
fun Description(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = White700,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )
}
