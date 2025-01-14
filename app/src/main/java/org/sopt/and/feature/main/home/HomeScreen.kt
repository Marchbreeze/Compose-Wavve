package org.sopt.and.feature.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomeRoute() {
    HomeScreen()
}


@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.background(Color.Gray)) {
        Text("Home")
    }
}