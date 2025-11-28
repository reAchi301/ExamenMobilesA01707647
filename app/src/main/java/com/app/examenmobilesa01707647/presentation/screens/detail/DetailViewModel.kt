package com.app.examenmobilesa01707647.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examenmobilesa01707647.domain.common.Result
import com.app.examenmobilesa01707647.domain.usecase.GetCovidStatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamenDetailViewModel @Inject constructor(
    private val getCovidStatsUseCase: GetCovidStatsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExamenDetailUiState())
    val uiState: StateFlow<ExamenDetailUiState> = _uiState.asStateFlow()

    // Carga el país principal
    fun fetchCountryDetails(countryName: String) {
        viewModelScope.launch {
            getCovidStatsUseCase(countryName).collect { result ->
                _uiState.update { state ->
                    when (result) {
                        is Result.Loading -> state.copy(isLoading = true, error = null)
                        is Result.Success -> state.copy(
                            isLoading = false,
                            stat = result.data.firstOrNull(),
                            error = if (result.data.isEmpty()) "No hay datos" else null
                        )
                        is Result.Error -> state.copy(
                            isLoading = false,
                            error = result.exception.message
                        )
                    }
                }
            }
        }
    }


    fun compareWith(countryName: String) {
        viewModelScope.launch {
            getCovidStatsUseCase(countryName).collect { result ->
                if (result is Result.Success) {
                    _uiState.update { it.copy(comparisonStat = result.data.firstOrNull()) }
                }
            }
        }
    }
}