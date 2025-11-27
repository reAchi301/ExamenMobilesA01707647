package com.app.examenmobilesa01707647.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.app.examenmobilesa01707647.presentation.theme.ExamenMobilesA01707647Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenMobilesA01707647Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //todo: Aquí vamos a comenzar
                }
            }
        }
    }
}
