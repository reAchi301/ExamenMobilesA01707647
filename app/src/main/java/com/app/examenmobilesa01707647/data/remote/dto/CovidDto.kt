package com.app.examenmobilesa01707647.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CovidDto(
    @SerializedName("country") val country: String,
    @SerializedName("region") val region: String,
    @SerializedName("cases") val cases: Map<String, CaseDetailDto>?
)

data class CaseDetailDto(
    @SerializedName("total") val total: Int,
    @SerializedName("new") val new: Int
)