package com.grupo3.smi_mobile_client_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.grupo3.smi_mobile_client_android.presentation.navigation.AppNavigation
import com.grupo3.smi_mobile_client_android.ui.theme.SmimobileclientandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appContainer = (application as SmiApplication).appContainer

        setContent {
            SmimobileclientandroidTheme {
                AppNavigation(appContainer = appContainer)
            }
        }
    }
}
