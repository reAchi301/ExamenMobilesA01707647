package com.app.examenmobilesa01707647.data.repository

import com.app.examenmobilesa01707647.data.remote.api.CovidApi
import com.app.examenmobilesa01707647.data.remote.dto.toDomain
import com.app.examenmobilesa01707647.domain.model.CovidStat
import com.app.examenmobilesa01707647.domain.repository.CovidRepository
import javax.inject.Inject

class CovidRepositoryImpl @Inject constructor(
    private val api: CovidApi
) : CovidRepository {

    override suspend fun getCovidStats(country: String): List<CovidStat> {
        return try {
            val response = api.getCovidData(country = country)
            response.take(10).map { it.toDomain() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}

