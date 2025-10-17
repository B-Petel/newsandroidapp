package com.bpetel.newsandroidapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class MetaDataDto(
    val page: Int = 0,
    @SerializedName("per_page") val perPage: Int = 0,
    @SerializedName("query_time_ms") val queryTimeMs: Int = 0,
    @SerializedName("total_hits") val totalHits: Int = 0,
    @SerializedName("total_pages") val totalPages: Int = 0
)