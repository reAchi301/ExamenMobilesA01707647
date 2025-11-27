package com.app.examenmobilesa01707647.domain.usecase

import com.app.examenmobilesa01707647.domain.common.Result
import com.app.examenmobilesa01707647.domain.model.CovidStat
import com.app.examenmobilesa01707647.domain.repository.CovidRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCovidStatsUseCase @Inject constructor(
    private val repository: CovidRepository
) {
    operator fun invoke(country: String): Flow<Result<List<CovidStat>>> = flow {
        try {
            emit(Result.Loading)
            val stats = repository.getCovidStats(country)
            if (stats.isNotEmpty()) {
                emit(Result.Success(stats))
            } else {
                emit(Result.Error(Exception("No se encontraron datos para $country")))
            }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}