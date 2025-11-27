package com.app.examenmobilesa01707647.data.remote.api

import com.app.examenmobilesa01707647.data.remote.dto.CovidDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CovidApi {
    @GET("v1/covid19")
    suspend fun getCovidData(
        @Header("X-Api-Key") apiKey: String = "uJ0NO8Z7Fr1tzkEz1Pk7lA==nXDKpIfQ1O63YjMa",
        @Query("country") country: String
    ): List<CovidDto>
}