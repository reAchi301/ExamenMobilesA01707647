package com.app.examenmobilesa01707647.domain.model

data class CovidStat(
    val country: String,
    val region: String,
    val date: String,
    val totalCases: Int,
    val newCases: Int
)