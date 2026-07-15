package com.grupo3.smi_mobile_client_android.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.grupo3.smi_mobile_client_android.domain.model.Colaborador
import com.grupo3.smi_mobile_client_android.presentation.components.AppBottomNav
import com.grupo3.smi_mobile_client_android.presentation.components.AppScaffold
import com.grupo3.smi_mobile_client_android.presentation.components.BottomNavItem
import com.grupo3.smi_mobile_client_android.presentation.viewmodel.ProfileViewModel
import com.grupo3.smi_mobile_client_android.ui.theme.SmiBorder
import com.grupo3.smi_mobile_client_android.ui.theme.SmiRed
import com.grupo3.smi_mobile_client_android.ui.theme.SmiSurfaceWhite
import com.grupo3.smi_mobile_client_android.ui.theme.SmiTextPrimary
import com.grupo3.smi_mobile_client_android.ui.theme.SmiTextSecondary

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onVolver: () -> Unit,
    onHomeClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    AppScaffold(
        topBar = { ProfileTopBar(onVolver = onVolver) },
        bottomBar = {
            AppBottomNav(
                seleccionado = BottomNavItem.PROFILE,
                onHomeClick = onHomeClick,
                onProfileClick = { }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
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
            } else if (uiState.colaborador != null) {
                ProfileContent(colaborador = uiState.colaborador!!)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileTopBar(onVolver: () -> Unit) {
    TopAppBar(
        title = { Text("Mi Perfil") },
        navigationIcon = {
            IconButton(onClick = onVolver) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = SmiSurfaceWhite)
    )
}

@Composable
private fun ProfileContent(colaborador: Colaborador) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
                    .background(SmiRed),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = inicialesDe(colaborador),
                    color = SmiSurfaceWhite,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "${colaborador.nombres} ${colaborador.apellidoPaterno} ${colaborador.apellidoMaterno}",
                color = SmiTextPrimary,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                text = colaborador.puesto,
                color = SmiRed,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SmiSurfaceWhite)
        ) {
            Column {
                CampoPerfil(label = "DNI", valor = colaborador.dni)
                CampoPerfil(label = "Edad", valor = "${colaborador.edad} años")
                CampoPerfil(label = "Área", valor = colaborador.area)
                CampoPerfil(label = "Puesto", valor = colaborador.puesto, mostrarDivider = false)
            }
        }
    }
}

@Composable
private fun CampoPerfil(label: String, valor: String, mostrarDivider: Boolean = true) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
        Text(text = label, color = SmiTextSecondary, style = MaterialTheme.typography.labelMedium)
        Text(
            text = valor,
            color = SmiTextPrimary,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
    if (mostrarDivider) {
        HorizontalDivider(color = SmiBorder)
    }
}

private fun inicialesDe(colaborador: Colaborador): String {
    val inicialNombre = colaborador.nombres.firstOrNull()?.uppercaseChar() ?: ' '
    val inicialApellido = colaborador.apellidoPaterno.firstOrNull()?.uppercaseChar() ?: ' '
    return "$inicialNombre$inicialApellido"
}
