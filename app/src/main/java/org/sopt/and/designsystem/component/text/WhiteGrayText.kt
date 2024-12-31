package org.sopt.and.designsystem.component.text

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun WhiteGrayText(
    modifier: Modifier,
    fontSize: Int,
    whiteText: String,
    grayText: String,
) {
    Row(
        modifier = modifier,
    ) {
        Text(
            text = whiteText,
            fontSize = fontSize.sp,
            color = Color.White,
        )
        Text(
            text = grayText,
            fontSize = fontSize.sp,
            color = Color.Gray,
        )
    }
}