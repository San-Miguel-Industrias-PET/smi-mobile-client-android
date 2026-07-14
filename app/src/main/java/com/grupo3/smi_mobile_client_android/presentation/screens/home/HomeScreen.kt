package com.grupo3.smi_mobile_client_android.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grupo3.smi_mobile_client_android.domain.model.Comunicado
import com.grupo3.smi_mobile_client_android.presentation.components.AppLogo
import com.grupo3.smi_mobile_client_android.presentation.components.AppScaffold
import com.grupo3.smi_mobile_client_android.ui.theme.SmiRed
import com.grupo3.smi_mobile_client_android.ui.theme.SmiSurfaceWhite
import com.grupo3.smi_mobile_client_android.ui.theme.SmiTextPrimary
import com.grupo3.smi_mobile_client_android.ui.theme.SmiTextSecondary

// HU02 (feed) esta en construccion por otro companero: por ahora se usa ComunicadosMock.
// HU04: filtrado de comunicados por categoria. Toda la logica de filtro vive aqui.
// El topBar/bottomBar son solo el marco visual del mockup (no requieren backend).
private val CATEGORIAS = listOf("Todos", "Seguridad", "Sostenibilidad", "Pagos", "Eventos", "Beneficios")

@Composable
fun HomeScreen() {
    var categoriaSeleccionada by remember { mutableStateOf("Todos") }

    val comunicadosFiltrados = remember(categoriaSeleccionada) {
        if (categoriaSeleccionada == "Todos") {
            ComunicadosMock.lista
        } else {
            ComunicadosMock.lista.filter { it.categoria == categoriaSeleccionada }
        }
    }

    AppScaffold(
        topBar = { HomeTopBar() },
        bottomBar = { HomeBottomNav() }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            FiltroCategorias(
                categorias = CATEGORIAS,
                seleccionada = categoriaSeleccionada,
                onSeleccionar = { categoriaSeleccionada = it }
            )
            ListaComunicados(comunicados = comunicadosFiltrados)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar() {
    TopAppBar(
        title = { AppLogo(modifier = Modifier.height(36.dp)) },
        navigationIcon = {
            IconButton(onClick = { /* TODO: abrir menu lateral (fuera del alcance de HU04) */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: ir a perfil (fuera del alcance de HU04) */ }) {
                Icon(Icons.Default.AccountCircle, contentDescription = "Perfil", tint = SmiRed)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = SmiSurfaceWhite)
    )
}

@Composable
private fun HomeBottomNav() {
    NavigationBar(containerColor = SmiSurfaceWhite) {
        NavigationBarItem(
            selected = true,
            onClick = { /* ya estamos en Home */ },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SmiSurfaceWhite,
                indicatorColor = SmiRed,
                selectedTextColor = SmiRed
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO: pantalla de notificaciones (fuera del alcance de HU04) */ },
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Notifications") },
            label = { Text("Notifications") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO: pantalla de perfil (fuera del alcance de HU04) */ },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FiltroCategorias(
    categorias: List<String>,
    seleccionada: String,
    onSeleccionar: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categorias) { categoria ->
            FilterChip(
                selected = categoria == seleccionada,
                onClick = { onSeleccionar(categoria) },
                label = { Text(categoria) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = SmiRed,
                    selectedLabelColor = SmiSurfaceWhite
                )
            )
        }
    }
}

@Composable
private fun ListaComunicados(comunicados: List<Comunicado>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(comunicados, key = { it.id }) { comunicado ->
            ComunicadoCard(comunicado)
        }
    }
}

@Composable
private fun ComunicadoCard(comunicado: Comunicado) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SmiSurfaceWhite)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = comunicado.categoria.uppercase(),
                    color = SmiRed,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = comunicado.fecha,
                    color = SmiTextSecondary,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Text(
                text = comunicado.titulo,
                color = SmiTextPrimary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = comunicado.resumen,
                color = SmiTextSecondary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
