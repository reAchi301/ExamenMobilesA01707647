package com.app.examenmobilesa01707647.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
// Commons
import com.app.examenmobilesa01707647.presentation.common.ErrorView
import com.app.examenmobilesa01707647.presentation.common.LoadingShimmer
// Import tarjeta
import com.app.examenmobilesa01707647.presentation.screens.home.components.CovidStatCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    var searchText by remember { mutableStateOf(state.lastCountry) }

    // Si al iniciar el ViewModel ya traía un país guardado, actualizamos la barra
    LaunchedEffect(state.lastCountry) {
        if (searchText.isBlank()) searchText = state.lastCountry
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                Text(
                    text = "COVID-19 Tracker 🦠",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Barra de Búsqueda
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Buscar País (ej. Canada, Mexico)", color = Color.White) },
                    trailingIcon = {
                        IconButton(onClick = { viewModel.searchCountry(searchText) }) {
                            Icon(Icons.Default.Search, contentDescription = "Buscar", tint = Color.White)
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when {
                // ESTADO 1: Cargando (Muestra Shimmer)
                state.isLoading -> {
                    Column(modifier = Modifier.padding(16.dp)) {
                        repeat(5) {
                            LoadingShimmer(modifier = Modifier.fillMaxWidth().height(100.dp).padding(bottom = 16.dp))
                        }
                    }
                }
                // ESTADO 2: Error (Muestra ErrorView con botón reintentar)
                state.error != null -> {
                    ErrorView(
                        message = state.error ?: "Error desconocido",
                        onRetry = { viewModel.searchCountry(searchText) },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                // ESTADO 3: Éxito (Muestra la lista de tarjetas)
                else -> {
                    if (state.stats.isEmpty()) {
                        Text("Ingresa un país para ver datos", modifier = Modifier.align(Alignment.Center))
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(state.stats) { stat ->
                                // Aquí usamos el componente que creamos en el paso 1
                                CovidStatCard(stat)
                            }
                        }
                    }
                }
            }
        }
    }
}