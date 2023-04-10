package fr.deuspheara.potterdbapp.data.network.model

import com.squareup.moshi.Json

data class PotterLinks(
    @Json(name = "self")
    val self: String,
    @Json(name = "current")
    val current: String,
    @Json(name = "next")
    val next: String?,
    @Json(name = "last")
    val last: String
)