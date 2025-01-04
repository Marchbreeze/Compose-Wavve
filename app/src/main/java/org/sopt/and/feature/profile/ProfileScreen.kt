package org.sopt.and.feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier
) {

    ProfileScreen()
}


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column {
        Text("Profile")
    }
}