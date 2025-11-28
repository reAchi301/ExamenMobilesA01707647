package com.app.examenmobilesa01707647.presentation.screens.detail

import com.app.examenmobilesa01707647.domain.model.CovidStat

data class ExamenDetailUiState(
    val stat: CovidStat? = null,
    val comparisonStat: CovidStat? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)