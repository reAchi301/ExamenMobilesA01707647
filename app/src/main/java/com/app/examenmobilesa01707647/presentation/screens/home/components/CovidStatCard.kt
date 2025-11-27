package com.app.examenmobilesa01707647.presentation.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.examenmobilesa01707647.domain.model.CovidStat

@Composable
fun CovidStatCard(stat: CovidStat) {
    //Rojo si es grave (>1000 casos nuevos), Verde si está controlado
    val cardColor = if (stat.newCases > 1000) Color(0xFFFFEBEE) else Color(0xFFE8F5E9)
    val textColor = if (stat.newCases > 1000) Color(0xFFB71C1C) else Color(0xFF1B5E20)

    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Encabezado: Región y Fecha
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stat.region, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = stat.date, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Datos de los casos
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Total Casos", style = MaterialTheme.typography.labelMedium)
                    Text(text = "${stat.totalCases}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Nuevos", style = MaterialTheme.typography.labelMedium)
                    Text(
                        text = "+${stat.newCases}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = textColor
                    )
                }
            }
        }
    }
}