package com.app.examenmobilesa01707647.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examenmobilesa01707647.data.local.CovidPreferences
import com.app.examenmobilesa01707647.domain.common.Result
import com.app.examenmobilesa01707647.domain.model.CovidStat
import com.app.examenmobilesa01707647.domain.usecase.GetCovidStatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// Definimos el estado de la UI aquí mismo para ir rápido
data class HomeUiState(
    val stats: List<CovidStat> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val lastCountry: String = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCovidStatsUseCase: GetCovidStatsUseCase,
    private val preferences: CovidPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        // Al iniciar, cargamos el último país visto automáticamente
        val lastCountry = preferences.getLastCountry()
        searchCountry(lastCountry)
    }

    fun searchCountry(country: String) {
        if (country.isBlank()) return

        viewModelScope.launch {
            // Guardamos la preferencia inmediatamente
            preferences.saveLastCountry(country)

            getCovidStatsUseCase(country).collect { result ->
                _uiState.update { state ->
                    when (result) {
                        is Result.Loading -> state.copy(isLoading = true, error = null, lastCountry = country)
                        is Result.Success -> state.copy(
                            isLoading = false,
                            stats = result.data,
                            error = null
                        )
                        is Result.Error -> state.copy(
                            isLoading = false,
                            error = result.exception.message ?: "Error desconocido"
                        )
                    }
                }
            }
        }
    }
}