package org.sopt.and.feature.main.save

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
    Column {
        Text("Save")
    }
}