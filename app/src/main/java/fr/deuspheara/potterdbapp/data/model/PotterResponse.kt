package fr.deuspheara.potterdbapp.data.model

import com.squareup.moshi.Json

data class PotterResponse<T>(
    @Json(name = "data")
    val data: PotterType<T>,
    @Json(name = "meta")
    val meta: PotterMeta,
    @Json(name = "links")
    val links: PotterLinks
)

data class PotterType<T>(
    @Json(name = "id")
    val id: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "attributes")
    val attributes: List<T>
)
