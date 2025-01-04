package org.sopt.and.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier
) {

    HomeScreen()
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Column {
        Text("Home")
    }
}