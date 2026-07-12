package com.grupo3.smi_mobile_client_android.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.grupo3.smi_mobile_client_android.presentation.components.AppScaffold
import com.grupo3.smi_mobile_client_android.ui.theme.SmiTextSecondary

// Placeholder de HU02 (muro de comunicados): por ahora solo confirma que el login redirige aquí.
@Composable
fun HomeScreen() {
    AppScaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Home", color = SmiTextSecondary)
        }
    }
}
