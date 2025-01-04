package org.sopt.and.designsystem.component.appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.sopt.and.designsystem.theme.ANDANDROIDTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    showBackBtn: Boolean = false,
    onBackClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.White
            )
        },
        actions = {
            if (showBackBtn) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingTopBarPreview() {
    ANDANDROIDTheme {
        OnboardingTopBar(
            title = "로그인",
            onBackClick = {},
            showBackBtn = true
        )
    }
}