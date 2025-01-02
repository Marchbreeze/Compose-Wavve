package org.sopt.and.designsystem.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun WhiteGrayText(
    fontSize: Int,
    whiteText: String,
    grayText: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White)) {
                append(whiteText)
            }
            withStyle(style = SpanStyle(color = Color.Gray)) {
                append(grayText)
            }
        },
        fontSize = fontSize.sp,
        modifier = modifier
    )
}