package com.app.examenmobilesa01707647.presentation.screens.detail

data class ExamenDetailUiState(
    val pokemon: Pokemon? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)