package org.sopt.and.feature.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.designsystem.theme.ANDANDROIDTheme
import org.sopt.and.feature.main.navigation.MainTab

@Composable
fun MainBottomBar(
    isVisible: Boolean,
    tabs: List<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        NavigationBar(containerColor = Color.Black) {
            tabs.forEach { itemType ->
                NavigationBarItem(
                    selected = currentTab == itemType,
                    onClick = {
                        onTabSelected(itemType)
                    },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = (itemType.icon)),
                            contentDescription = itemType.contentDescription,
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                        )
                    },
                    label = {
                        Text(
                            text = itemType.contentDescription,
                            fontSize = 9.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.LightGray,
                        unselectedTextColor = Color.LightGray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainBottomBarPreview() {
    ANDANDROIDTheme {
        MainBottomBar(
            isVisible = true,
            tabs = MainTab.entries.toList(),
            currentTab = MainTab.HOME,
            onTabSelected = {}
        )
    }
}