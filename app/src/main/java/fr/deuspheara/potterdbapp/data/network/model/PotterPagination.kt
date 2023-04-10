package fr.deuspheara.potterdbapp.data.network.model

import com.squareup.moshi.Json

data class PotterPagination(
    @Json(name = "current")
    val current: Int,
    @Json(name = "next")
    val next: Int?,
    @Json(name = "last")
    val last: Int,
    @Json(name = "records")
    val records: Int
)

