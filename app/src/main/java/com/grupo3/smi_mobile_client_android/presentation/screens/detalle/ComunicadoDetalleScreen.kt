package com.grupo3.smi_mobile_client_android.presentation.screens.detalle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.grupo3.smi_mobile_client_android.domain.model.Comunicado
import com.grupo3.smi_mobile_client_android.presentation.components.AppScaffold
import com.grupo3.smi_mobile_client_android.presentation.viewmodel.ComunicadoDetalleViewModel
import com.grupo3.smi_mobile_client_android.ui.theme.SmiBackground
import com.grupo3.smi_mobile_client_android.ui.theme.SmiRed
import com.grupo3.smi_mobile_client_android.ui.theme.SmiSurfaceWhite
import com.grupo3.smi_mobile_client_android.ui.theme.SmiTextPrimary
import com.grupo3.smi_mobile_client_android.ui.theme.SmiTextSecondary

@Composable
fun ComunicadoDetalleScreen(
    viewModel: ComunicadoDetalleViewModel,
    onVolver: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    AppScaffold(
        topBar = { DetalleTopBar(onVolver = onVolver) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = SmiRed)
                }
            } else if (uiState.error != null) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = uiState.error!!, color = SmiRed, modifier = Modifier.padding(16.dp))
                    Button(onClick = { viewModel.retry() }) {
                        Text("Reintentar")
                    }
                }
            } else if (uiState.comunicado != null) {
                ComunicadoDetalleContent(comunicado = uiState.comunicado!!)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetalleTopBar(onVolver: () -> Unit) {
    TopAppBar(
        title = { Text("Detalle de la noticia") },
        navigationIcon = {
            IconButton(onClick = onVolver) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = SmiSurfaceWhite)
    )
}

@Composable
private fun ComunicadoDetalleContent(comunicado: Comunicado) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = comunicado.categoria.uppercase(),
            color = SmiRed,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = comunicado.titulo,
            color = SmiTextPrimary,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 8.dp)
        )

        Row(modifier = Modifier.padding(top = 8.dp)) {
            if (comunicado.autor.isNotBlank()) {
                Text(
                    text = comunicado.autor,
                    color = SmiTextSecondary,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "  •  ",
                    color = SmiTextSecondary,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Text(
                text = comunicado.fecha,
                color = SmiTextSecondary,
                style = MaterialTheme.typography.labelMedium
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        if (comunicado.resumen.isNotBlank()) {
            Text(
                text = comunicado.resumen,
                color = SmiTextPrimary,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(SmiBackground)
                    .padding(12.dp)
            )
        }

        if (comunicado.contenido.isNotBlank()) {
            Text(
                text = comunicado.contenido,
                color = SmiTextPrimary,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
            )
        }
    }
}
