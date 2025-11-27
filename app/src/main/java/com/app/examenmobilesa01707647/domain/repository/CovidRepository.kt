package com.app.examenmobilesa01707647.domain.repository

import com.app.examenmobilesa01707647.domain.model.CovidStat

interface CovidRepository {
    suspend fun getCovidStats(country: String): List<CovidStat>
}