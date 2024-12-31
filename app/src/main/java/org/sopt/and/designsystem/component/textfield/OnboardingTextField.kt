package org.sopt.and.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.designsystem.theme.ANDANDROIDTheme

@Composable
fun OnboardingTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "",
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val visualTransformation =
        if (isPassword && !isPasswordVisible) PasswordVisualTransformation()
        else VisualTransformation.None

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.DarkGray)
            .padding(8.dp),
        value = value,
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder,
                color = Color.LightGray
            )
        },
        trailingIcon = {
            if (isPassword) {
                Text(
                    text = if (isPasswordVisible) "hide" else "show",
                    color = Color.White,
                    modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                )
            }
        },
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.DarkGray,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingTextFieldPreview() {
    ANDANDROIDTheme {
        OnboardingTextField(
            value = "123456",
            placeholder = "힌트",
            onValueChange = {},
            isPassword = true
        )
    }
}