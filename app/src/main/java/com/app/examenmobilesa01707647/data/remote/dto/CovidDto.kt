package com.app.examenmobilesa01707647.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.app.examenmobilesa01707647.domain.model.CovidStat

data class CovidDto(
    @SerializedName("country") val country: String,
    @SerializedName("region") val region: String,
    @SerializedName("cases") val cases: Map<String, CaseDetailDto>?
)

data class CaseDetailDto(
    @SerializedName("total") val total: Int,
    @SerializedName("new") val new: Int
)


fun CovidDto.toDomain(): CovidStat {
    val latestEntry = cases?.entries?.lastOrNull()
    val date = latestEntry?.key ?: "N/A"
    val data = latestEntry?.value

    return CovidStat(
        country = country,
        region = region.ifEmpty { "General" },
        date = date,
        totalCases = data?.total ?: 0,
        newCases = data?.new ?: 0
    )
}