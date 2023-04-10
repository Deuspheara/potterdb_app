package fr.deuspheara.potterdbapp.data.network.model

import com.squareup.moshi.Json

data class PotterMeta(
    @Json(name = "pagination")
    val pagination: PotterPagination,
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "generated_at")
    val generatedAt: String
)