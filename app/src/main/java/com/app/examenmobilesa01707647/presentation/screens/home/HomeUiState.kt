package com.app.examenmobilesa01707647.presentation.screens.home

data class HomeUiState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

