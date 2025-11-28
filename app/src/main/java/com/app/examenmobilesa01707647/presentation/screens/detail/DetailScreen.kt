package com.app.examenmobilesa01707647.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.examenmobilesa01707647.domain.model.CovidStat
import com.app.examenmobilesa01707647.presentation.common.ErrorView
import com.app.examenmobilesa01707647.presentation.common.LoadingShimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    countryName: String,
    onBack: () -> Unit,
    viewModel: ExamenDetailViewModel = hiltViewModel() // Asegúrate que sea el ExamenDetailViewModel
) {
    LaunchedEffect(countryName) {
        viewModel.fetchCountryDetails(countryName)
    }

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(countryName) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when {
                state.isLoading -> LoadingShimmer(Modifier.padding(16.dp).fillMaxWidth().height(200.dp))
                state.error != null -> ErrorView(message = state.error ?: "Error", onRetry = { viewModel.fetchCountryDetails(countryName) })
                state.stat != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // VISTA NORMAL (País seleccionado)
                        StatCard(title = "Datos de ${state.stat!!.country}", stat = state.stat!!)

                        Spacer(modifier = Modifier.height(24.dp))

                        // SECCIÓN DE COMPARATIVA (+10 Pts)
                        if (state.comparisonStat == null) {
                            Button(
                                onClick = { viewModel.compareWith("Mexico") }, // Compara con México por defecto
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Comparar con México (+10 Pts)")
                            }
                        } else {
                            Text(" COMPARATIVA ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.height(8.dp))
                            StatCard(title = "Datos de ${state.comparisonStat!!.country}", stat = state.comparisonStat!!, isComparison = true)
                        }
                    }
                }
            }
        }
    }
}

// Componente reutilizable para las tarjetas de estadísticas
@Composable
fun StatCard(title: String, stat: CovidStat, isComparison: Boolean = false) {
    val bgColor = if (isComparison) Color(0xFFE3F2FD) else MaterialTheme.colorScheme.surfaceVariant

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Totales", style = MaterialTheme.typography.labelMedium)
                    Text("${stat.totalCases}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Nuevos", style = MaterialTheme.typography.labelMedium)
                    Text("+${stat.newCases}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = if(stat.newCases > 1000) Color.Red else Color(0xFF2E7D32))
                }
            }
        }
    }
}