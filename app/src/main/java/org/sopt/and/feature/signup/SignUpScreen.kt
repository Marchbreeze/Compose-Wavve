package org.sopt.and.feature.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignUpRoute(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel(),
    navigateUp: (String, String) -> Unit
) {