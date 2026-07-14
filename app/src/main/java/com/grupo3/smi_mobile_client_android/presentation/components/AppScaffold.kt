package com.grupo3.smi_mobile_client_android.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.grupo3.smi_mobile_client_android.presentation.event.EventBus
import com.grupo3.smi_mobile_client_android.presentation.event.UiEvent

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        EventBus.events.collect { event ->
            val status = when (event) {
                is UiEvent.Success -> SnackbarStatus.SUCCESS
                is UiEvent.Error -> SnackbarStatus.ERROR
                is UiEvent.Warning -> SnackbarStatus.WARNING
            }
            snackbarHostState.showSnackbar(
                UiEventSnackbarVisuals(message = event.mensaje, status = status)
            )
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                val visuals = data.visuals as? UiEventSnackbarVisuals
                SnackbarCustom(data = data, status = visuals?.status ?: SnackbarStatus.ERROR)
            }
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}
