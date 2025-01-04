package org.sopt.and.feature.main.save

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SaveRoute(
    modifier: Modifier = Modifier
) {

    SaveScreen()
}


@Composable
fun SaveScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.background(Color.DarkGray)) {
        Text("Save")
    }
}