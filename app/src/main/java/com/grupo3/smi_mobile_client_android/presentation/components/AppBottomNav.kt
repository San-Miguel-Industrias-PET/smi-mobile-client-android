package com.grupo3.smi_mobile_client_android.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.grupo3.smi_mobile_client_android.ui.theme.SmiRed
import com.grupo3.smi_mobile_client_android.ui.theme.SmiSurfaceWhite

enum class BottomNavItem { HOME, NOTIFICATIONS, PROFILE }

@Composable
fun AppBottomNav(
    seleccionado: BottomNavItem,
    onHomeClick: () -> Unit,
    onNotificationsClick: () -> Unit = {},
    onProfileClick: () -> Unit
) {
    val colors = NavigationBarItemDefaults.colors(
        selectedIconColor = SmiSurfaceWhite,
        indicatorColor = SmiRed,
        selectedTextColor = SmiRed
    )

    NavigationBar(containerColor = SmiSurfaceWhite) {
        NavigationBarItem(
            selected = seleccionado == BottomNavItem.HOME,
            onClick = onHomeClick,
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            colors = colors
        )
        NavigationBarItem(
            selected = seleccionado == BottomNavItem.NOTIFICATIONS,
            onClick = onNotificationsClick,
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Notifications") },
            label = { Text("Notifications") },
            colors = colors
        )
        NavigationBarItem(
            selected = seleccionado == BottomNavItem.PROFILE,
            onClick = onProfileClick,
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            colors = colors
        )
    }
}
